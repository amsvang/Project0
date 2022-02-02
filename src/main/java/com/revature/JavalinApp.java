package com.revature;

import com.revature.controllers.*;
import com.revature.util.LoggingUtil;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {

    private final LoggingUtil loggingUtil = new LoggingUtil();
    private final AppExceptionHandler appExceptionHandler = new AppExceptionHandler();
    private AuthController authController = new AuthController();
    private final UserController userController = new UserController();
    private final EmployeeController employeeController = new EmployeeController();
    private final CustomerController customerController = new CustomerController();
    private final TransactionController transactionController = new TransactionController();
    private final AccountController accountController = new AccountController();

    private Javalin app = Javalin.create().routes(()->{
        path("manager-access",()->{
            before(authController::authorizeManageToken);
            // All manager routes...
        });
        path("employee-access",()->{
            before(authController::authorizeEmployeeToken);
            path("users",()->{
                get(userController::handleGetAll);
                post(userController::handleCreate);
                delete(userController::handleDelete);
                path("{id}",()->{
                    get(userController::handleGetOne);
                    put(userController::handleUpdate);
                });
            });
            path("employee",()->{
                get(employeeController::handleGetAll);
                post(employeeController::handleCreate);
                delete(employeeController::handleDelete);
                path("{id}",()->{
                    get(employeeController::handleGetOne);
                    put(employeeController::handleUpdate);
                });
            });
            path("customer",()->{
                get(customerController::handleGetAll);
                post(customerController::handleCreate);
                delete(customerController::handleDelete);
                path("{id}",()->{
                    get(customerController::handleGetOne);
                    put(customerController::handleUpdate);
                });
            });
            path("transaction",()->{
                get(transactionController::handleGetAllTransactions);
                post(transactionController::handleCreate);
                delete(transactionController::handleDelete);
                path("{id}",()->{
                    get(transactionController::handleGetOne);
                    put(transactionController::handleUpdate);
                });
            });
            path("account",()->{
                get(accountController::handleGetAllAccounts);
                post(accountController::handleCreate);
                delete(accountController::handleDelete);
                path("{id}",()->{
                    get(accountController::handleGetOne);
                    put(accountController::handleUpdate);
                });
            });
        });
        path("customer",()->{
            before(authController::authorizeCustomerToken);
            path("transfer",()->{
                post(customerController::handleTransfer);
            });
            path("deposit", ()->{
                post(customerController::handleDeposit);
            });
        });
        path("login", ()->{
            post(authController::authenticateLogin);
        });
        before("*",loggingUtil::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);

    public void start(int port){
        app.start(port);
    }
}
