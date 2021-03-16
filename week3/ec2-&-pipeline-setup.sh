/home/ec2-user is where you are


====== EC2 SETUP ===========

sudo yum install java-1.8.0-openjdk-devel -y
sudo yum install maven -y
sudo yum install git -y
sudo amazon-linux-extras install tomcat8.5 -y

cd /etc/tomcat/

sudo sed -i 's/<Connector port=\"8080\"/<Connector port=\"8085\"/' /etc/tomcat/server.xml


sudo service tomcat restart 

==============go to the port at the public amazon dns:8080 --------

sudo git clone <-project->

cd <project>

sudo mvn package

============== mvn package will compile your code and package it 
============== For example, if your pom says the project is a jar, it will create a jar for you when you package it and put it somewhere in the target directory (by default).

sudo cp target/YourProject-0.0.1-SNAPSHOT.war /var/lib/tomcat/webapps/

============  ^ this means, as super user, copy the war file (which we produce with MVN package) and hands it to tomcat webapps folder to deploy

============ Your project is running at http://ec2-dns.amazonaws.com:8085/YourProject-0.0.1-SNAPSHOT


=====================JENKINS SETUP=====================

//pulls the repo so that jenkins can be installed.

sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins.io/redhat-stable/jenkins.repo

sudo rpm --import http://pkg.jenkins.io/redhat-stable/jenkins.io.key

sudo yum install -y jenkins

sudo service jenkins start

Go to: http://your-ec2-dns.amazonaws.com:8085

TO GET PASSWORD: 
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

--------- Set up everything -- --------------

--> new item --> name it --> Freestyle Project which allows SCM
	In Jenkins, SCM stands for "Source Code Management". This option instructs Jenkins to obtain your Pipeline from Source Control Management (SCM), which will be your locally cloned Git repository.

==== BUILD TRIGGERS tell us when to build the project

Go to Add Build Step >> see Execute Shell >> 

echo "hello jenkins"
cd YourProject
mvn package
cp target/YourProject-0.0.1-SNAPSHOT.war /var/lib/tomcat/webapps/

Save, run the build, then view the console output notice maven package success.
