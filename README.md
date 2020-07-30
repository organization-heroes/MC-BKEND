# MC-BKEND
Micro-credential program
> To get started...

### Step 1

- **Clone the Repository**
    - 👯 Clone this repo to your local machine using `https://github.com/organization-heroes/MC-BKEND.git`
	- For configuration purpose find this URL `https://github.com/organization-heroes/MC-BKEND-CONFIG.git`

### Step 2

- **Crate a folder for H2DB**
    - `C:\data`	
	

### Step 3

- **Build Wisely**
    - Build individual app as per the sequence in the root pom.xml
	- Start the application once build and build the next application to avoid build error!

### Step 4

- **Run Wisely**
    - Run individual app as per the sequence in the root pom.xml except the common as it's a common library

### Step 5
- **Build sonar way**
	- Run Sonar Qube locally
	- Use the aggregator-service and run the following code

```Sonar Qube
// code away!

mvn sonar:sonar -Dsonar.projectKey=mc-bkend -Dsonar.host.url=http://localhost:9000 -Dsonar.login=yoursonarqube-key
```

### Step 6

- **Go to `localhost:88800/swagger-ui.html` and HACK AWAY!** 🔨🔨🔨

### Step 7

- **Log Analysis using ELK**
    - Run Elastic Search
	- Run Logstash using `"..\bin\logstash" -f "logstash-v2.conf"` going into the config folder of logstash and find the logstash-v2.conf in the root repository.
	- Run Kibana
---

## All in one

From API Gateway(Zuul Proxy), Hystrix, Feign, O-Auth, H2-DB as a service, Swagger and lots more. Dig into the application and explore things are required for spring cloud micro services architechture.

---

## Support

Reach out to me at one of the following places!

- Email at <a href="sukanta.workspace@gmail.com" target="_blank">`Gmail`</a>
- LindIn at <a href="https://www.linkedin.com/in/sukanta-banerjee-6ab375100?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BrVVEwMzaRlGWsp3vJ7utzQ%3D%3D" target="_blank">`@linkedIn`</a>

---


## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2020 © <a href="https://github.com/sukantaworkspace" target="_blank">Sukanta Workspace</a>.
