# Changelog

## v5
- Updated Clean-Code-Architecture diagram to better reflect dependency inversion.
- Clarified that Domain implements the API interface.
- Emphasized that Domain specifies the SPI interface which is implemented by Client/Infrastructure.
- Note that Domain is Pure Java to adhere with clean architecture principles.
- These changes better illustrate how Domain remains the central element that controls both API and SPI contracts

## v4
- Integrated HCP Vault for static and dynamic secrets management.

## v3
- Updated HLD architecture based on whiteboard design (HLD-v3-client-side-only). 
- Replaced the Application Load Balancer (ALB) with a Network Load Balancer (NLB) to enhance performance and minimize latency.
- Reengineered the client-side application to act solely as a gRPC client, enabling faster communication and supporting real-time bidirectional streaming.
- Eliminated the API Gateway to simplify the architecture and align with the revised project scope.
- Substituted the polling mechanism for ride status updates with an event-driven approach, improving scalability, decoupling services, and enabling communication only when updates occur.

## v2
- Renamed microservices name in HLD diagram to reflect the exact purpose.
- Enhanced font size of the HLD image for better readability.
- Added Fleet's Clean Code Architecture Diagram.

## v1
- Initial version of the HLD diagram and source file.
- Basic architecture representation based on the initial whiteboard design (HLD-v1).