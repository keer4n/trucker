IoT RESTful API project in Spring boot

1. Develop following REST endpoints for ingestion from: [http://mocker.egen.io]
	- Load vehicle details in bulk via a PUT /vehicles endpoint.
	- If the vehicle with same VIN is already present, update the record in db.
	- Ingest readings from these vehicles via a POST /readings.
2. Create alerts with given priority when following rules are triggered
	- Rule: engineRpm > readlineRpm, Priority: HIGH	    
	- Rule: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM
	- Rule: tire pressure of any tire < 32psi || > 36psi , Priority: LOW
	- Rule: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW
3. Develop REST end points for:
	- Fetch details of all the vehicles like VIN, make, model, year etc.
	- Fetch HIGH alerts within last 2 hours for all the vehicles and ability to sort list of vehicles based on it.
	- Ability to list vehicle's geolocation within last 30minutes on a map.
	- Ability to list a vehicle's all historical alerts
4. Launch one AWS VPC with subnets and security groups
5. Configure Docker Swarm cluster on the AWS 
6. Configure Jenkins to run a build job after every commit on your repo and deploy to aws cluster
7. Use Route53 to configure DNS and Health checks for your service
