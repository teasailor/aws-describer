# AWS describer
AWS client collects and stores data in own DB.

## Business requirements
Prepare a Spring Boot application, which would:
1. Connect to AWS
2. Collect information about EC2 Instances, Security Groups and Volumes
3. Identify relations between the objects (Instance, Security Group, Volume)
4. Store collected info into DB of your choice (describe, why specific choice is made)

## Questions and answers
What info are you interested in (about Instances, Security Groups and Volumes)? Is it report format to show once?
> anything you find useful/helpful for user

Isn’t it needed to update information about EC2?
> yes, would be great

Isn’t it needed to have public API/UI to see collected information?
> please make it verifiable. API/UI is your choice (please explain the decision taken)  

## Open questions
1. Authorization and authentication
2. Security (hide credentials, mask sensitive data from logs...)
3. Performance improvements (load description by pages)
4. Thread-safe operations (lock resources)
7. Who are clients? (change API, create view models)
   1. Provide possibility to search for unlinked security groups
   1. Add instance relation to security group
11. To AWS specialist: does AWS provide a way to subscribe on an update?


### TODO
* Review and clean up business model and entities
* Unit testing coverage
* Integration test with AWS API mock
* Logging system
* Documentation of API (swagger)
* Tracing and monitoring
* Code analysis (Sonar)
* Refreshable configs (config server)
* Versioning, docker...
