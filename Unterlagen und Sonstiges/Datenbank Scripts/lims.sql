create table analysen
(
    id        int         not null,
    api       varchar(30) not null,
    bemerkung varchar(30) not null,
    constraint analysen_id_uindex
        unique (id)
);

alter table analysen
    add primary key (id);

create table eigenschaften
(
    eigenschft_key varchar(30) not null
        primary key,
    value          varchar(30) not null
)
    charset = latin1;

create table mitarbeiter
(
    mitarbeiterID int auto_increment,
    vorname       varchar(30) null,
    nachname      varchar(30) null,
    passwort      varchar(30) null,
    constraint mitarbeiter_mitarbeiterID_uindex
        unique (mitarbeiterID)
)
    charset = latin1;

alter table mitarbeiter
    add primary key (mitarbeiterID);

create table partner
(
    vertragsnummer int         not null,
    name           varchar(30) not null,
    email          varchar(30) null,
    constraint partner_vertragsnummer_uindex
        unique (vertragsnummer)
);

alter table partner
    add primary key (vertragsnummer);

create table projekte
(
    projekt_id     varchar(30) not null,
    vertragsnummer int         not null,
    constraint projekte_projekt_id_uindex
        unique (projekt_id),
    constraint projekte_partner_vertragsnummer_fk
        foreign key (vertragsnummer) references partner (vertragsnummer)
)
    charset = latin1;

alter table projekte
    add primary key (projekt_id);

create table substanz
(
    substanz_id varchar(30) not null,
    projekt_id  varchar(30) not null,
    constraint substanz_substanz_id_uindex
        unique (substanz_id),
    constraint substanz_projekte_projekt_id_fk
        foreign key (projekt_id) references projekte (projekt_id)
)
    charset = latin1;

alter table substanz
    add primary key (substanz_id);

create table probe
(
    proben_nr   varchar(30) not null,
    substanz_id varchar(30) not null,
    constraint probe_proben_nr_uindex
        unique (proben_nr),
    constraint probe_substanz_substanz_id_fk
        foreign key (substanz_id) references substanz (substanz_id)
)
    charset = latin1;

alter table probe
    add primary key (proben_nr);

create table experiment
(
    id        int         not null,
    proben_nr varchar(30) null,
    constraint experiment_id_uindex
        unique (id),
    constraint experiment_probe_proben_nr_fk
        foreign key (proben_nr) references probe (proben_nr)
)
    charset = latin1;

alter table experiment
    add primary key (id);

create table experimente_analysen
(
    experiment int not null,
    analyse    int not null,
    primary key (experiment, analyse),
    constraint experimente_analysen_analysen_id_fk
        foreign key (analyse) references analysen (id),
    constraint experimente_analysen_experiment_id_fk
        foreign key (experiment) references experiment (id)
);

create table experimenttyp
(
    id  int         not null,
    typ varchar(30) not null,
    constraint experimenttyp_id_uindex
        unique (id),
    constraint experimenttyp_experiment_id_fk
        foreign key (id) references experiment (id)
);

alter table experimenttyp
    add primary key (id);

create table experimenttyp_grinding
(
    id int not null,
    constraint experimenttyp_grinding_id_uindex
        unique (id),
    constraint experimenttyp_grinding_experimenttyp_id_fk
        foreign key (id) references experimenttyp (id)
            on update cascade on delete cascade
);

alter table experimenttyp_grinding
    add primary key (id);

create table experimenttyp_slurry
(
    id    int auto_increment,
    value int null,
    constraint experimenttyp_slurry_id_uindex
        unique (id),
    constraint experimenttyp_slurry_experimenttyp_id_fk
        foreign key (id) references experimenttyp (id)
            on update cascade on delete cascade
);

alter table experimenttyp_slurry
    add primary key (id);

create table substanz_eigenschaften
(
    substanz    varchar(30) not null,
    eigenschaft varchar(30) not null,
    primary key (substanz, eigenschaft),
    constraint substanz_eigenschaften_eigenschaften_eigenschft_key_fk
        foreign key (eigenschaft) references eigenschaften (eigenschft_key),
    constraint substanz_eigenschaften_substanz_substanz_id_fk
        foreign key (substanz) references substanz (substanz_id)
)
    charset = latin1;

