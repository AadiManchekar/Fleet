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

echo -e "\033[0;32mACTION: mvn clean\033[0m"  # Dark green
print_line_break
# Clean up old build files
echo "Cleaning up old build files..."
mvn clean
print_line_break

echo -e "\033[0;32mACTION: mvn install -DskipTests\033[0m"  # Dark green
print_line_break
mvn install -DskipTests && print_success || print_error
