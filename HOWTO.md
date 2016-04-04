# PMX Atlassian Plugin - HowTo use

PMX Atlassian Plugin is written for the last Atlassian Jira software version (Atlassian JIRA Project Management Software v7.1.1) and can be installed using the standard JAR upload procedure (described [here](https://confluence.atlassian.com/display/UPM/Installing+add-ons "Installing add-ons")).

## Prepare system
1. Check all the prerequisites following the steps [here](https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project/set-up-the-sdk-prerequisites-for-linux-or-mac)
2. Install the Atlassian SDK following the steps [here](https://developer.atlassian.com/docs/getting-started/set-up-the-atlassian-plugin-sdk-and-build-a-project/install-the-atlassian-sdk-on-a-linux-or-mac-system)

## Build the plugin
1.  Ensure Atlassian SDK is installed
2.  Checkout the project and build & package plugin using Atlassian SDK (open terminal -> "cd" into root plugin folder -> execute `atlas-package` command) 
3.	Deploy the plugin by use application's administration console of your JIRA distribution (click on **Upload add-on** link and choose `people-management-extensions-plugin-0.1-SNAPSHOT.jar` file from your hard drive, then click **Upload**  
4.	If a confirmation message appears, the add-on is successfully installed
5.	Restart your application to have your change take effect 
6.	Enjoy!

## Run JIRA development application:
1.  Ensure Atlassian SDK is installed
2.  Checkout the project
3.	From root of plugin folder, execute `atlas-run` command to run JIRA application with the plugin installed
4.	Open the link [http://127.0.0.1:2990/jira](http://127.0.0.1:2990/jira "Open JIRA") in your favorite browser
5.	Enjoy!
