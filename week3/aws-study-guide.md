# ⁉️ AWS & Cloud Computing QC Questions
*The following are questions that you may be asked about Amazon Web Services and Cloud Computing Models.*

1. What is cloud computing?
2. What are the 3 models of cloud computing? *(Explain IaaS, PaaS, SaaS).*
3. List 3 advantages of cloud computing over the traditional on-premises model.
4. What is AWS?
5. What is Amazon RDS?
6. What types of RDBMS Vendors does Amazon RDS support?
7. What are security groups?
8. How do you connect to an Amazon RDS?
9. What is Amazon EC2?
10. What is EBS and how is it used in relation to an EC2?
11. How does Amzon EC2 differ from Amazon RDS?
12. Name 2 ways to connect to an Linux EC2 Instance. *Hint: Check out AWS documentation [here](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/AccessingInstances.html).  We connected using **one** of these methods in class.*
13. What is an AMI (Amazon Machine Image)? How is it used in relation to and EC2?

<hr>


# AWS Study Guide
AWS (Amazon Web Services) is a PaaS (Platform as a Service) which offers many SaaS (Software as a Service) and IaaS (Infrastructure as a Service) IT solutions on the cloud (i.e. hosted remotely on their many server farms). Amazon offers many services at various price points and dominates the cloud IT market today.

## EC2
EC2, Elastic Cloud Compute, is a web service providing flexible computing power in the form of a server running a headless operating system on virtual hardware.An instance is a single EC2 machine with predefined hardware resources (CPU, RAM, Network I/O), and can be configured individually or as part of a group of instances in a VPC (Virtual Private Cloud). Security Group settings, Auto-Scaling groups, Load Balancers, and EBS (Elastic Block Storage) drives can be attached to each instance. When creating a new instance, users must choose an AMI from Amazon or the Amazon marketplace, attach an EBS drive, and create or assign existing key pairs for secure SSH connections to the instance in the future.

### AMI
An AMI (Amazon Machine Image) is a snapshot of a preconfigured operating system ready to be flashed onto a new EC2 instance. Amazon offers many Linux and Windows AMIs, including its own Linux distribution 'Amazon Linux', all of which come with pre-installed tools and services as well as proper security settings and user account configurations.

### Security Group
EC2 instances have strict firewall settings by default and must be configured through security group settings to allow specific kinds of traffic through specific ports or port ranges. By default a security group allows incoming SSH protocol connections on port 22.

### Auto Scaling and Elastic Load Balancing
An EC2 instance can be horizontally scaled, i.e. multiple instances sharing the same configuration can be deployed or removed to provide a small army of servers hosting multiple instances of your EC2 machine. Auto Scaling groups provide users control over hardware or network thresholds that trigger automatic scaling of the number of instances, while an ELB (Elastic Load Balancer) manages efficient routing of traffic to these instances to evenly spread traffic to available resources.

### Connecting to an EC2 Instance
The standard protocol for connections to an EC2 instance is SSH (Secure Shell). Using the Unix `ssh` command or a Windows tool like `PuTTY` a user can establish a connection using a `.pem` key file which contains a private/public key pair. When an EC2 instance is created, the complimentary key value is stored on the instance as well and all connections must use this file to authenticate.

Using `ssh` to connect:
```bash
ssh -i 'path/to/your.pem' ec2UserName@ec2-IP-or-DNS
```

For Amazon Linux AMIs, the username is `ec2-user`. For an Ubuntu AMI, it would be `ubuntu`. The server can be reached via its short IPv4 address or the generated DNS name, both of which are generated for your EC2 instance. Be wary of any address changes after instances are shut down.

When first connecting, answer `yes` to the prompt asking to register the new server. Afterwards your shell prompt will now reflect a session on your EC2.

### EBS
Elastic Block Store (EBS) provides hard storage for EC2 instances, with various hardware options for desired performance and capacity. EC2 memory is volatile, so EBS offers a file storage option to back up any data on an EC2 instance. Furthermore, EBS volumes can be detached and reattached at will, and even cloned or attached to completely new instances.

EBS volumes are formatted with a file system for compatibility with an EC2 instance's operating system.

# Linux
Linux is a popular choice for a cloud server. There are a variety of open-source distributions each with a large community of hobbyists and professionals supporting them. Unique development tools are widespread and simple to install, use, and maintain. And most distributions are very capable without the resource overhead of a graphical desktop user interface, making the system very lightweight.

On a headless server (without a graphical interface), the most popular way to interact with a server is through a terminal interface. The most widespread is a shell known as Bash, the Borne Again Shell.

## Shell
A shell is a program that interprets user input. Emulating the look and feel of an old mainframe terminal interface, many shells like Bash provide their users access to several command-line interface (CLI) tools as well as useful conveniences such as auto-completion, a scripting language syntax, and I/O redirection.

To use a CLI tool a user types the name of a program followed by any parameters or flags.

