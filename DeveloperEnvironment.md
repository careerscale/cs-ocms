# Summary OCMS development setup

# Introduction #

This page is primarily to setup the developer environment in his/her computer.

The tech stack used in the applicatioin

## Tech stack ##
  1. Tiles, HTML, CSS, Javascript (JQuery and others etc).
> > JSP, Java (Spring MVC)
  1. Spring framework (IOC, Spring DAO,mail)
  1. Hibernate 4, JPA
  1. Third party APIs (Scribe for OAuth, Xpath, apache lang, commons etc).
  1. MYSQL
  1. Maven
  1. SVN
  1. JUnit, Mockito
  1. Selenium (Proposed) for FT
  1. Web services and REST services (proposed)



# Pre-requisites #


  1. JDK 7  http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
  1. Eclipse juno http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr1
  1. SVN plugin installed  http://subclipse.tigris.org/update_1.8.x
  1. Maven plugin to eclipse installed http://download.eclipse.org/technology/m2e/releases
  1. [MySQL database and work bench(single installer) ](http://www.mysql.com/downloads/installer/)

You can install these plugins from Eclipse Market place as well.

# Getting the code #

  * SVN URL for the code base https://cs-ocms.googlecode.com/svn/trunk/ocms
Then check out this code and configure the project in eclipse. Try building it.

Initially it will take lot of time to build as it would be downloading
lot of  jars from internet. it might even fail after downloading some
files, please try few more times.

# Building and running the code #

right click on the pom.xml and maven -> install to build.


how do we run this project?

right click on pom.xml

"Run as" -> Run configurations -> then enter

clean install tomcat7:run  in the maven goal input field.

It would take some time and then  console will show you it has started
tomcat (if successful), if not you need to resolve any issues.

We will do this in a online session in gotomeeting on 1st Evening if
that is ok for all of you. if someone is going to miss, well, that is
ok, we will publish this on youtube and can be accessed later.

# Useful reading #

  1. Maven: http://www.sonatype.com/books/mvnref-book/reference/
  1. SVN : http://svnbook.red-bean.com  and also
  1. spring framework,
  1. spring MVC
  1. Hibernate
  1. MySQL database and queries.
http://dev.mysql.com/doc/refman/5.0/en//resetting-permissions.html

# Good to have plugins #
# http://www.eclemma.org/installation.html
# http://findbugs.sourceforge.net/manual/eclipse.html

# Project related videos #
http://www.youtube.com/playlist?list=PLUt5z7bPvuPL6yi0rYnVVZmB71-AFIr8x

Java trainings http://www.youtube.com/playlist?list=PLF118AF9DB836E150&feature=view_all

= http://www.thegeekstuff.com/2009/07/change-reset-mysql-root-password-on-unix-linux