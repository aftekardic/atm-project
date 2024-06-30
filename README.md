# ATM Project

The ATM Project provides functionalities for various banking transactions such as user registration, secure login, deposits and withdrawals through the database.

### Key Features

---

1. **User Registration and Login:**

   - **Registration:** New customers must register by providing a email, name, surname and password. Passwords are securely hashed using Spring Security before being stored in the database.
   - **Login:** Registered customers can log in by entering their credentials.

2. **Account Management:**

   - **Deposit Funds: **Customers can add money to their account.
   - **Withdraw Funds:** Customers can withdraw money from their account, provided they have sufficient funds. The system validates the balance before processing withdrawals.
   - **Account Settings:** Users can change account information such as name, surname or email. They cannot change the password and account role.

3. **Validation and Error Handling:**
   - The system ensures that withdrawals are completed only if the client has sufficient funds. Insufficient funds will result in the transaction being rejected and an error message. Completed transactions are also returned to the user as an information message.

### Prerequisites

---

- Java SDK 17
- Maven
- NodeJS minimum version → v20.13.1

### Installation

---

#### Cloning the Repository

Once Git is installed, clone the repository to your local machine:

1. Open a terminal or command prompt.

2. Navigate to the directory where you want to clone the repository.

3. Run the following command:
```
git clone https://github.com/aftekardic/atm-project.git
```

4. Navigate into the cloned repository:

```
cd atm_project
```

#### Running the Application

1. Open the project in your IDE.

2. Build the project using Maven:

```
mvn clean install
```

3.  Run the application:

    - **Backend**

      - Additional Setup

         1. Configure your database settings in src/main/resources/application.properties.

         2. Ensure you have the necessary environment variables set up for database access and other configurations.

      - Run backend application
        ```
        cd backend
        mvn spring-boot:run
        ```
        or you can run from BackendApplication.java run button.

    - **Frontend**

      - Additional Setup

         1. Firstly you can set the .env file for frontend requests.

         2. Open the .env file in the frontend home directory and configure it according to your needs. After you can run the frontend application.

      - Run frontend application
        ```
        cd frontend
        npm run start
        ```

### API Usage

---

If you are using backend only, you should first get a token from the /authenticate endpoint, then you can use other endpoints by adding `Bearer <your_token>` to the Authorization of the Header.

| HTTP Method | Endpoint                   | Description                        |
| ----------- | -------------------------- | ---------------------------------- |
| POST        | /authenticate              | Authenticate user and obtain token |
| POST        | /register                  | Register a new user                |
| POST        | /logOut                    | Terminate user session             |
| POST        | /validateToken             | Validate token                     |
| GET         | /api/v1/role/all           | List all roles                     |
| POST        | /api/v1/role/add           | Add a new role                     |
| DELETE      | /api/v1/role/delete/{id}   | Delete a role                      |
| GET         | /api/v1/admin/all-users    | List all users                     |
| PUT         | /api/v1/admin/update/{id}  | Update user information            |
| DELETE      | /api/v1/admin/delete/{id}  | Delete a user                      |
| GET         | /api/v1/user/info/{id}     | Get user information               |
| GET         | /api/v1/user/amount/{id}   | Get user account balance           |
| PUT         | /api/v1/user/update/{id}   | Update user information            |
| PUT         | /api/v1/user/deposit/{id}  | Deposit money into user account    |
| PUT         | /api/v1/user/withdraw/{id} | Withdraw money from user account   |

### Security Configurations

---

This project includes security configurations to ensure proper authentication and authorization for accessing various endpoints. Here's an overview of the security components and their functionalities:

1. **CORS Configuration**
   The CORS (Cross-Origin Resource Sharing) configuration allows defining which origins are allowed to access the resources of this application. It is configured to allow access from http://localhost:3000 and supports HTTP methods GET, POST, PUT, and DELETE in CorsConfig.java file.

2. **Security Filter**
   The security filter intercepts incoming requests and performs token validation to ensure authenticated access to protected endpoints. It checks for the presence and validity of JWT tokens and sets the authentication context accordingly in SecurityFilter.java file.

3. **Token Provider**
   The token provider is responsible for generating and validating JWT (JSON Web Token) tokens used for authentication. It utilizes a secret key to sign the tokens and sets expiration time for access tokens in TokenProvider.java file.

4. **Web Security Configuration**
   The web security configuration defines access rules for different endpoints based on user roles. It configures security filters and session management policies to ensure proper handling of authentication and authorization in WebSecurityConfig.java file.

**_Feel free to customize these configurations according to your project's specific requirements and security policies. If you have any questions or need further assistance, please let me know!_**

## Conclusion

Congratulations! You've reached the end of the README for my ATM project. By now, you should have a good understanding of the project structure, its functionalities, and the security configurations in place.

I hope this README serves as a comprehensive guide to help you get started with the project, understand its various components, and make any necessary customizations to fit your specific requirements.

If you have any questions, encounter issues, or would like to contribute to the project, feel free to reach out to me. Your feedback and contributions are highly appreciated!
