#!/usr/bin/env python3
import requests

BASE_URL = "http://localhost:9426"
ACTUATOR_URL = f"{BASE_URL}/actuator"


def test_endpoints():
    endpoints = ['health', 'info', 'metrics', 'prometheus']
    for endpoint in endpoints:
        response = requests.get(f"{ACTUATOR_URL}/{endpoint}")
        assert response.status_code == 200, f"Expected 200 OK for {endpoint}, got {response.status_code}"
        content = response.text
        assert content, f"Expected non-empty response for {endpoint}"


if __name__ == "__main__":
    print("Running Python integration tests for dummy-child-module")
    test_endpoints()
    print("All tests passed.")