# Application Flow

### User Sign up

1. User chooses signup on the main menu (available on all pages, unless the user
is signed in already).
1. User fills out the sign up form and user input are validated and submits.
1. Request goes to sign up servlet.

1. If not error, Servlet creates a user object and then creates user in the database.
1. Response to user confirming addition (show either a success message on the jsp)
1. If there is an error, the servlet will respond with an error message in JSP page.

### User Sign In

1. User chooses sign in on the menu (available on all pages, unless the user
is signed in already).
1. User enters username and password on form and submits.
1. If user is authenticated, the server will handle allowing access to addition web content available to authenticated people only.
1. If user is admin will have access to edit pages and delete objects functionality. JDBCRealm used for authentication (users, users_roles, and roles table).
1. If authentication fails, show error message/page.

### Profile

1. User chooses Profile on the menu (available on all pages, if the user
is signed in).
1. User will be redirected to the profile page to view their info and access to edit button to change their info.
1. The server will send a get request to retrieve data from database.
1. Response to user with the data available in the database.

### User Profile Edit

1. User chooses Edit Profile button available on User profile if user is logged in.
1. User enters All fields they desire to change on form and submits.
1. If all the form data are valid, Servlet update a user profile object and then update user in the database.
1. Response to user confirming that the edition is complete with a success message.
1. If the user profile form data are invalid, an error message will be displayed back to user with what is wrong.
1. User is redirected back to the user profile.


### Change password

1. User chooses change password on main menu (available on all pages if user is already logged in).
1. User enters desired new password and confirm that password on form and submits.
1. If the password is valid, Servlet update the password in the user object and then update password field in the database.
1. Response to user confirming that the edition is complete with a success message.
1. If the change password form data are invalid, an error message will be displayed back to user with what is wrong.
1. User will have ability to return the User profile page.


### Reset password

1. User chooses Reset password available on Sign In/Login page which accessible to not logged in users.
1. User is redirected send link page
1. A link to reset password is sent to the provide email.
1. User enter the desired new password and submits
1. If the password is valid, Servlet replace the password in the user object and then update password field in the database.
1. Response to user confirming that the new password was created with a success message.
1. If the change password form data are invalid, an error message will be displayed back to user with what is wrong.
1. User will have ability to return the User profile page.

### View either Film or Genre or Book or Category or Short story or FAQ Trailer or Music

1. Page sends a request to view either Film or Genre or Book or Category or Short story or FAQ Trailer or Music servlet along with criteria of what to get back to the user based on the object selected.
1. Servlet uses the either Film or Genre or Book or Category or Short story or FAQ Trailer or Music dao to select either Film or Genre or Book or Category or Short story or FAQ Trailer or Music object according to criteria
1. Dao performs select and creates either Film or Genre or Book or Category or Short story or FAQ Trailer or Music objects from results.
1. Dao returns list of either Film or Genre or Book or Category or Short story or FAQ Trailer or Music object matching criteria to servlet.
1. Servlet sends list back to either Film or Genre or Book or Category or Short story or FAQ Trailer or Music list (Object).jsp (either Film or Genre or Book or Category or Short story or FAQ Trailer or Music).
1. Film or Genre or Book or Category or Short story or FAQ Trailer or Music jsp displays the reports.
1. Use Pagination to view a long list of

### View either Film or Genre or Book or Category or Short story or FAQ Trailer or Music Detail

1. Page sends a request to view a single either Film or Genre or Book or Category or Short story or FAQ Trailer or Music object servlet along with criteria.
1. Servlet uses the either Film or Genre or Book or Category or Short story or FAQ Trailer or Music dao to select a single according to criteria
1. Dao performs select and creates either Film or Genre or Book or Category or Short story or FAQ Trailer or Music object from result.
1. Dao returns details of a single Film or Genre or Book or Category or Short story or FAQ Trailer or Music matching criteria to servlet.
1. Servlet sends details back to either Film or Genre or Book or Category or Short story or FAQ Trailer or Music details jsp.

### Contact

1. User enters contact information
1. Information is sent to Contact form servlet
1. Servlet creates contact object
1. Servlet sends object to the preconfigured email to receive the information

### Add Trail Report

1. Option only available to logged in users with proper role
1. User selects trail to report on
1. User enters trail report details
1. Details are sent to Add Trail Report servlet
1. Servlet creates trail report object
1. Servlet sends object to dao
1. Dao adds report to the database
1. Servlet sends confirmation to report page that report has been added.

### Add/ Edit Film or Genre or Book or Category or Short story or FAQ Trailer or Music or Casting Crew
1. Option only available to logged in users with proper role
1. User enters Film or Genre or Book or Category or Short story or FAQ Trailer or Music details
1. Details are sent to Add/ Edit Film or Genre or Book or Category or Short story or FAQ Trailer or Music or Casting Crew servlet
1. Servlet creates Film or Genre or Book or Category or Short story or FAQ Trailer or Music or Casting Crew object
1. Servlet sends Add/ Edit Film or Genre or Book or Category or Short story or FAQ Trailer or Music or Casting Crew object to dao
1. Dao adds Film or Genre or Book or Category or Short story or FAQ Trailer or Music or Casting Crew to the database
1. Servlet sends confirmation message that Add/ Edit Film or Genre or Book or Category or Short story or FAQ Trailer or Music or Casting Crew has been added or updated
