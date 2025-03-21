#!/bin/bash -e

function print_line_break {
    echo "==========================================================================="
}

function print_success {
    print_line_break
    echo -e "\033[0;32mSUCCESS\033[0m"  # Dark green
    print_line_break
    exit 0
}

function print_error {
    print_line_break
    echo -e "\033[0;31mERROR\033[0m"  # Red
    print_line_break
    exit 1
}

print_line_break
cd $(dirname "$0")/../../
echo -e "\033[0;33mDIRECTORY: $(pwd)\033[0m"  # Dark yellow
print_line_break

# MVN CLEAN
echo -e "\033[0;32mACTION: mvn clean\033[0m"  # Dark green
print_line_break
echo "Cleaning up old build files..."
mvn clean
print_line_break

# Skip docker compose up if running in GitHub CI as it is already up by previous stage
if [ "$GITHUB_ACTIONS" != "true" ]; then
    echo -e "\033[0;32mACTION: docker compose up -d\033[0m"  # Dark green
    print_line_break
    echo "Starting up the docker compose..."
    cd tools/docker
    docker-compose up -d
    cd ../..
    print_line_break
fi

# MVN INSTALL
echo -e "\033[0;32mACTION: mvn install\033[0m"  # Dark green
print_line_break
mvn install && print_success || print_error

