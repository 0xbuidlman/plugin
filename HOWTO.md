# PMX Atlassian Plugin - HowTo use

PMX Atlassian Plugin is written for the last Atlassian Jira software version (Atlassian JIRA Project Management Software v7.1.1) and can be installed using the standard JAR upload procedure (described [here](https://confluence.atlassian.com/display/UPM/Installing+add-ons "Installing add-ons")).

In detail:


1.  Install Atlassian SDK for your operation system (if not already done)
2.  Checkout the project and build & package plugin using Atlassian SDK (open terminal -> "cd" into root plugin folder -> execute `atlas-package` command) 
3.	Deploy the plugin by use application's administration console of your JIRA distribution (click on **Upload add-on** link and choose `people-management-extensions-plugin-0.1-SNAPSHOT.jar` file from your hard drive, then click **Upload**  
4.	If a confirmation message appears, the add-on is successfully installed
5.	Restart your application to have your change take effect 
6.	Enjoy!

For developer purpose:

1.  Install Atlassian SDK for your operation system (if not already done)
2.  Checkout the project
3.	From root of plugin folder, execute `atlas-run` command to run JIRA application with the plugin installed
4.	Open the link [http://127.0.0.1:2990/jira](http://127.0.0.1:2990/jira "Open JIRA") in your favorite browser
5.	Enjoy!