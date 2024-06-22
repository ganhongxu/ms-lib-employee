Create table t_department(
    id uuid NOT NULL,
    department_name varchar(255) NOT NULL,
    CONSTRAINT t_department_pk UNIQUE (id)
);

Create table t_employee(
    id uuid NOT NULL,
    name varchar(255) NOT NULL,
    country varchar(255) NOT NULL,
    age int4 NOT NULL,
    department_id uuid NOT NULL,
    CONSTRAINT t_employee_pk UNIQUE (id),
    CONSTRAINT t_department_fk FOREIGN KEY (department_id) REFERENCES t_department(id)
);

