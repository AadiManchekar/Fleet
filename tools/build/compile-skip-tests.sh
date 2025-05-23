#!/bin/bash -e

function print_line_break {
    echo "==========================================================================="
}

function print_success {
    print_line_break
    echo -e "\033[0;32mSUCCESS\033[0m" # Dark green
    print_line_break
    exit 0
}

function print_error {
    print_line_break
    echo -e "\033[0;31mERROR\033[0m" # Red
    print_line_break
    exit 1
}

print_line_break
cd $(dirname "$0")/../../
echo -e "\033[0;33mDIRECTORY: $(pwd)\033[0m" # Dark yellow
print_line_break

echo -e "\033[0;32mACTION: Building dumm-child-module, SKIP_TEST=true\033[0m" # Dark green
print_line_break

docker build \
    --build-arg MODULE_NAME=dummy-child-module \
    --build-arg PORT=9426 \
    --build-arg SKIP_TESTS=true \
    -t fleet/dummy-child-module \
    -f tools/docker/Dockerfile . && print_success || print_error
