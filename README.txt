Robert Gerwig
Comcast Code Test
REST Service to support partner ad campaigns

Configuration - This is a stand alone war file and no configuration is neccessary. I ran it using Tomcat by just dropping it into the webapps directory and starting Tomcat.

URLS 
http://<Host:Port>/adserver/ad - GET returns list of all ads in cache
http://<Host:Port>/adserver/ad - POST with properly formed Json will add to cache. If validation errors occur it will return http code 400 with a list of the issues. It also implements the POST invariant from the instructions.
http://<Host:Port>/adserver/ad/{partner_id} - GET with valid partner_id will return ad json as long as the ad is active. If inactive (per instructions) it will return a message. If the partner_id does not yield an ad it will return a message as well. 

Note: Usually REST nouns work from plural to singular. I used the url /ad per the code test instructions, however I would have implemented it with the url /ads in a real world situation. 

