# Fleet
Fleet is my attempt at building a functional and scalable cab aggregator application while honing my problem-solving and decision-making skills. Every technical and architectural choice comes with trade-offs, and I aim to document each one, providing insights into the challenges and solutions involved in building such a platform.

----

## Why Fleet Uses GPL 3.0
Fleet is licensed under GPL 3.0 to promote open collaboration and ensure that any modifications or improvements made by others remain open-source. This aligns with the project's goal of learning and sharing knowledge while fostering a community-driven development approach.

----

## Requirements

### Functional Requirements
Fleet must provide the following core functionalities to ensure a seamless user experience:
- **Real-Time Driver Location Tracking**: Continuously track and update driver locations.
- **Ride Booking**: Allow users to book rides, view ride details and ride status.
- **Ride Allocation System**: Implement a system to allocate rides efficiently.


### Non-Functional Requirements
To ensure the system is robust, scalable, and maintainable, the following non-functional requirements must be met:
- **High Performance**:
  - Low response time for API calls.
  - Low latency for real-time updates.
  - High throughput to handle multiple concurrent requests.
- **Scalability**:
  - Support horizontal scaling to handle increased traffic.
  - Database design must support partitioning and sharding for efficient data management.
- **Maintainability**:
  - Follow modular design principles for easier updates and debugging.
  - Ensure clean architecture and clean code practices.
- **Monitoring and Logging**:
  - Log all critical events for debugging.
  - Use tools like OpenTelemetry for real-time monitoring and observability.

----

## Architecture

### Infrastructure Components

### Diagrams

### Microservice Design Decisions
This section outlines the key decisions made for each microservice in the Fleet application:

----

## Challenges Faced
Building Fleet came with its own set of challenges, which provided valuable learning opportunities:


## Learnings Along the Way
Developing Fleet has been a rewarding experience, offering numerous insights and learnings:

1. **Formatting** was a big challenge and wanted to maintain consitent formatting, found **spotless maven plugin** that solves most of the things and we can specify formatting for even various types of lang
    - links referred:
        - https://www.baeldung.com/java-maven-spotless-plugin
        - https://github.com/diffplug/spotless/blob/main/plugin-maven/README.md


----

## Installation

### Prerequisites
- Java (v17)
- Apache Maven (v3.9.9 or higher)
- Docker (v27.3.1 or higher)
- Docker compose (v2.30.3-desktop.1 since im using windows)
- Python (v3.8.10)
### Steps to Build the Application
To build the Fleet application, execute the following command:
```bash
# Clean and build the application
mvn clean install
```

### Steps to Run Pre-Commit Hooks
Follow these steps to ensure pre-commit hooks are installed and executed correctly:

1. **If Pre-Commit is Already Installed**:
  Run the following commands:
  ```bash
  pre-commit install
  pre-commit run --all
  ```

2. **If Pre-Commit is Not Installed**:
  Ensure you are using Python 3.8.10 (newer Python and pre-commit versions may have compatibility issues). Then, execute:
  ```bash
  pip install -r requirements.txt
  pre-commit install
  pre-commit run --all
  ```

3. **If Using Windows and Pre-Commit Command is Not Found**:
  Verify your Python version by running:
  ```bash
  python3 --version
  ```
  Ensure the output is:
  ```
  Python 3.8.10
  ```

  Then, use the following commands to install and run pre-commit hooks:
  ```bash
  python3 -m pre_commit install
  python3 -m pre_commit run --all
  ```


----

## Contributing
- Direct pushes to the `main` branch are protected and not allowed. All changes must go through a pull request.
- Once a pull request is merged, the corresponding branch will be deleted to keep the repository clean.

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Submit a pull request with a detailed description of your changes.
