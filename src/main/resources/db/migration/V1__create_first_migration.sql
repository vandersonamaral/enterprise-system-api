CREATE TABLE address
(
    id         BIGINT PRIMARY KEY UNIQUE NOT NULL,
    city       VARCHAR(45)               NOT NULL,
    country    VARCHAR(60)               NOT NULL,
    fu         VARCHAR(255)              NOT NULL,
    number     BIGINT                    NOT NULL,
    postalCode VARCHAR(45)               NOT NULL,
    street     VARCHAR(150)              NOT NULL
);

CREATE TABLE employee
(
    id                       BIGINT PRIMARY KEY UNIQUE NOT NULL,
    birthdate                DATE                      NOT NULL,
    cpf                      VARCHAR(60)               NOT NULL,
    gender                   VARCHAR(255)              NOT NULL,
    name                     VARCHAR(60)               NOT NULL,
    salary                   DOUBLE                    NOT NULL,
    fk_employee_address_idx  BIGINT                    NOT NULL,
    fk_employee_employee_idx BIGINT,
    FOREIGN KEY (fk_employee_address_idx) REFERENCES address (id),
    FOREIGN KEY (fk_employee_employee_idx) REFERENCES employee (id)
);

CREATE TABLE department
(
    id     BIGINT PRIMARY KEY UNIQUE NOT NULL,
    name   VARCHAR(60)               NOT NULL,
    number BIGINT                    NOT NULL
);

CREATE TABLE project
(
    id         BIGINT PRIMARY KEY UNIQUE NOT NULL,
    cost       DOUBLE                    NOT NULL,
    end_date   DATE                      NOT NULL,
    name       VARCHAR(60)               NOT NULL,
    start_date DATE                      NOT NULL,
    value      DOUBLE                    NOT NULL
);

CREATE TABLE employee_product
(
    id                               BIGINT PRIMARY KEY UNIQUE NOT NULL,
    role                             VARCHAR(60)               NOT NULL,
    fk_employee_project_employee_idx BIGINT,
    fk_employee_project_project_idx  BIGINT,
    FOREIGN KEY (fk_employee_project_employee_idx) REFERENCES employee (id),
    FOREIGN KEY (fk_employee_project_project_idx) REFERENCES project (id)
);

CREATE TABLE address_budget
(
    id                       BIGINT PRIMARY KEY UNIQUE NOT NULL,
    description              VARCHAR(60)               NOT NULL,
    fu                       VARCHAR(255)              NOT NULL,
    number                   BIGINT                    NOT NULL,
    postalCode               VARCHAR(45)               NOT NULL,
    street                   VARCHAR(150)              NOT NULL,
    fk_budget_department_idx BIGINT                    NOT NULL,
    FOREIGN KEY (fk_budget_department_idx) REFERENCES department (id)
);
