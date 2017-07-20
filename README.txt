Robert Gerwig
Comcast Code Test
REST Service to support partner ad campaigns

Configuration - This is a stand alone war file and no configuration is neccessary. I ran it using Tomcat by just dropping it into the webapps directory and starting Tomcat. The war file is located in the root directory.

Builds: This is a maven based java project. You can build it yourself using mvn clean install from the command line. The new war will be located in the /target directory.

URIS 

http://<Host:Port>/adserver/ad - POST with properly formed Json will add to cache. If validation errors occur it will return http code 400 with a list of the issues. It also implements the POST invariant from the instructions.
http://<Host:Port>/adserver/ad/{partner_id} - GET with valid partner_id will return ad json as long as the ad is active. If inactive (per instructions) it will return a message. If the partner_id does not yield an ad it will return a message as well. 

Bonus URI
http://<Host:Port>/adserver/ad - GET returns list of all ads in cache

Additional URI - just for fun 
http://<Host:Port>/adserver/ad/{partner_id} - PUT with valid partner_id will update the ad campaign in the cache. If the partner_id does not yield an ad it will return a message stating that they should POST the ad for the partner.
http://<Host:Port>/adserver/ad/{partner_id} - DELETE with valid partner_id will return status 501, not implemented and a message.

Tests: There are some Junit tests. In a real world situation I would have more tests. I used Postman to do live testing.

Validation: The json body is validated when applicable and will return status code 400 with a message if invalid. For example if you put a String into the duration value it will kick back a validation message. 

Note: Usually REST nouns work from plural to singular. I used the url /ad per the code test instructions, however I would have implemented it with the url /ads in a real world situation. 

