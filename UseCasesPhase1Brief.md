Contains use cases for phase 1 at brief.

# Introduction #
This document is work in progress. Expect lot of edits in this page.
Once a use case definition is completely written, please move the same to UseCasesPhase1 page. This may happen even during the development.


# Administrative Use Cases #

## Case Type ##
Case Types and Sub Types are possible.
Education/Medical/orphan/old age home/Livelyhood etc

Sub types examples:
Education ->Primary,Secondary, +2, Medical,Engineering, PG etc
Medical -> Heart/Kindey/Cancer etc.

## Case Status Type ##
possible values are
  1. Registered
  1. Verification
  1. Verified
  1. Authorized
  1. In Progress
  1. Financial closure
  1. Bills\Receipts Uploaded
  1. Reviewed
  1. Closed
  1. Rejected
  1. Cancelled


## Help Type ##
help Type/ and sub types are possible.
Financial/Doctor advice/Legal/Govt scheme etc.

## Organization Type ##
Possible values are
Govt organization, NGO, Corporate company, Private Ltd,Partnership firm, Hospital, School/univerity etc.

## Individual type ##
Possible values are
  1. Monthly contributor(Contribute monthly amount to the organization)
  1. Volunteer(Got free time and attitude to do physical volunteering)
  1. Teacher(can teach as needed by the organization), Doctor (Can attend to patients)

This list and other master data values to be configurable from administrative pages.

## RBAC (Role Based Access Control )  configuration ##



## Application wide Configuration ##

  1. Password attempts
  1. Scheduled emails on/off
  1. <Add any other items that come across to this list >

## User management ##
  1. Add Users directly
  1. Reset password
  1. Delete user /Obsolute user

## Organization Management ##
  1. Add an organization
  1. Modify an organization

# Technical Use Cases #

## Open ID Authentication ##
This is about integrating with social networks for authentication and registration.

## Emailing framework ##
Define and implement an emailing framework for the entire project.
  * Emails with dynamic information
  * Template management.
  * Plain text/Rich text(HTML) mails.

## Schedule Jobs ##
Define a framework to create schedule jobs that can do a defined tasks
Basically we may use QUARTZ for scheduling but how tasks can be done need to be defined.

Our primarily interest here is to send scheduled notifications on activities within site to the users.


# Functional Use Cases #

## Registration (Individuals) ##
An individual user can register within our website in the following means.

### Direct registration ###
Fill a form that may include the following properties or more.
  1. Email Id (to be used as user id for authentication)
  1. Password (implement password and confirm password at UI level).
  1. First Name
  1. Last Name
  1. Date of Birth
  1. Individual Types(s)
  1. Profession (should we have a master list here?)
  1. Mobile Number  (Allow maximum of 3 numbers, users might have more than one).
  1. Phone (Allow maximum of 3)
  1. Alternate email ids (Maximum of 2)
  1. Address ( house no, locality, area/mandal, city/district, State, Country (Do we need this yet?), postal code/zip
  1. Blood group
  1. Case types interested
  1. Help that can be contributed by me.

### Open ID registration ###
Integrate with the following social networks and try fetching the data as mentioned for direct registration.
(We need to figure out what are the data points that can be fetched from Open ID authentication).

We need to make sure user registration is successful with basic information and then on subsequent login, we should show user to fill further details (or even skip if he is busy).


## Authentication ##

### Direct Login(user name/password) ###
Provide a login page with user name (email id), password.  On successful login, user should be able to access home page.

We need to login invalid login attempt and lock the account after a pre-configured number of attempts (say 5 or so).


## Case Life Cycle ##

A Case is nothing but a registration of a need. It may be posted by a user or admin staff. The needy details need to be captured during registration of case.

Various phases that a case can have are:
Registered ->In Verification ->Verified -> Authorized -> In Progress -> Financial closure -> Bills\Receipts Uploaded ->Reviewed -> Closed

Other possible states are Rejected, Cancelled.

We should allow changes in possible values
  1. Add new status
  1. Modify existing one
  1. Make certain status values to be skipped for certain case types/cases.
  1. Based on user feedback we need to alter the flows as needed.

At high level, case life cycle is the very core and complex functionality of the OCMS. This is one of the USP of the OCMS.