#!/bin/bash -e

# Define color codes as variables
GREEN="\033[0;32m"
RED="\033[0;31m"
YELLOW="\033[0;33m"
RESET="\033[0m" # Reset to default color

function print_line_break {
    echo "==========================================================================="
}

function print_success {
    print_line_break
    echo -e "${GREEN}SUCCESS${RESET}"
    print_line_break
}

function print_error {
    print_line_break
    echo -e "${RED}ERROR${RESET}"
    print_line_break
    exit 1
}

print_line_break
cd $(dirname "$0")/../../
echo -e "${YELLOW}DIRECTORY: $(pwd)${RESET}"
print_line_break

echo -e "${GREEN}ACTION: Building dummy-child-module${RESET}"
print_line_break

docker build \
    --no-cache \
    --build-arg MODULE_NAME=dummy-child-module \
    --build-arg PORT=9426 \
    -t fleet/dummy-child-module \
    -f tools/docker/Dockerfile . && print_success || print_error

# Integration tests
print_line_break
echo -e "${YELLOW}Executing Integration tests: $(pwd)${RESET}"
print_line_break

# If running in GitHub CI, skip docker compose up
if [ "$GITHUB_ACTIONS" != "true" ]; then
    echo -e "${GREEN}ACTION: docker compose up -d${RESET}"
    print_line_break
    echo "Starting up the docker compose..."
    cd tools/docker
    docker compose up -d
    cd ../..
    print_line_break
fi

# wait for the containers to be up and running
sleep 20 

# execute integration tests
echo -e "${YELLOW}Creating virtual environment 'fleet-it'${RESET}"
python3 -m venv fleet-it
echo -e "${YELLOW}Activating virtual environment 'fleet-it'${RESET}"
source fleet-it/bin/activate
echo -e "${YELLOW}Installing Python dependencies...${RESET}"
pip install -r tools/build/requirements.txt
echo -e "${YELLOW}Running Python integration tests...${RESET}"
python dummy-child-module/src/test/python/main.py
# currently, we are not having any integration tests

# # If running in GitHub CI, skip docker compose down
if [ "$GITHUB_ACTIONS" != "true" ]; then
    echo -e "${GREEN}ACTION: docker compose down${RESET}"
    print_line_break
    echo "Stopping the docker compose..."
    cd tools/docker
    docker compose down
    cd ../..
    print_line_break
fi