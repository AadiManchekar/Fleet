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
    exit 0
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

echo -e "${GREEN}ACTION: Building dummy-child-module, SKIP_TEST=true${RESET}"
print_line_break

docker build \
    --no-cache \
    --build-arg MODULE_NAME=dummy-child-module \
    --build-arg PORT=9426 \
    --build-arg SKIP_TESTS=true \
    -t fleet/dummy-child-module \
    -f tools/docker/Dockerfile . && print_success || print_error
