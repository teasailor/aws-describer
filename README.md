# aws-analizer
AWS client collects and stores data in own DB

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
3. Performance improvements
4. Thread-safe operations (lock resources)
5. Logging system
6. Tracing and monitoring
4. Who are clients? Change API?
4. Documentation of API (swagger)
5. Code analysis
1. To AWS specialist: does AWS provide a way to subscribe on an update?
