//todo  не работает команда exit на сервере - зависает в процессе выполнения, процесс можно прервать  Ctrl+C


//todo  БД не создается автоматически, если ее нет


//todo  нужно поправить com.golem.core.schemas.basicInterfaces.QueenCell#extractFactorie:
//  -    <T extends AbstractSystemCellFactory> List<T> extractFactories(ModuleLayer layer);
//  +    List<AbstractSystemCellFactory> extractFactories(ModuleLayer layer);
//  а в реализациях сделать так:
//  -        return list;
//  +        return new ArrayList<>(list);


//todo  нужно в build.gradle основного проекта добавить в секцию dependencies все модули, реализующие команды, примерно так:
// +    implementation project(':TerminalHelpCell')
// тогда gradle сам будет копировать jar в папку exp/server
// нужно в IDEA выполнить Code -> Inspect code -> Whole project -> Analyze и исправить все предупреждения какие получится.
// По непонятным местам - спросить.


//todo  предлагаю дать возможность задавать параметры подключения к базе данных из командной строки через системные
// свойства. Для этого в DatabaseManager нужно исправить:
//  -    private final String test_url = "jdbc:postgresql://localhost:5432/test1";
//  -    private final String test_user = "postgres";
//  -    private final String test_password = "pgAdmin";
//  +    private final String test_url = "jdbc:postgresql://  " +
//  +            System.getProperty("dbHost","localhost") + ":" +
//  +            System.getProperty("dbPort","5432") + "/" +
//  +            System.getProperty("dbName","test1");
//  +    private final String test_user = System.getProperty("dbUser", "postgres");
//  +    private final String test_password = System.getProperty("dbPassword","pgAdmin");
//  и в строку запуска добавить: -DdbName=test1 -DdbPort=5432 -DdbUser=postgres -DdbPassword=pgAdmin


//todo  com.golem.database.DatabaseManager#newID - вредный метод. Не надо так делать, удали его.
//  Правильная стратегия создания нового тикета следующая:
//  1) в БД вставляется запись venues у которой id = null. Поскольку id имеет тип serial, в поле автоматически
//  записывается уникальное значение из venues_id_seq.
//  2) делается select currval('venues_id_seq') результат которого записывается в поле id java-класса Venue
//  3) в БД вставляется запись coordinates у которой id = null. Поскольку id имеет тип serial,
//  в поле автоматически записывается уникальное значение из coordinates_id_seq.
//  4) делается select currval('coordinates_id_seq') результат которого записывается в поле id java-класса Coordinate
//  5) в БД вставляеся запись в tickets у которой id=null , _coordinates_id = currval('coordinates_id_seq'),
//  venue_id = currval('venues_id_seq')
//  4) делается select currval('ticket_id_seq') результат которого записывается в поле id java-класса Ticket
//  5) транзакция коммитится вызовом connection.commit() , после чего новый тикет окончательно записывается в БД
//  6) если транзакция завершилась успешно, тикет добавляется в коллекцию в памяти.


//todo  включить режим явных транзакций нужно в com.golem.database.DatabaseManager#init вызовом
//  connection.setAutoCommit(false);

//todo  При обновлении тикета последовательность гораздо короче - все id уже известны и никаких select currval()
// выполнять уже не требуется

//todo  com.golem.database.DatabaseManager#delete(java.lang.String)
//  com.golem.database.DatabaseManager#delete(com.golem.ticketCell.collection.ticket.Ticket)
//  нельзя в одном вызове executeUpdate указывать несколько sql-команд. на каждую нужно свой отдельный вызов

//todo  com.golem.database.DatabaseManager#checkID
//  1) по условиям лабораторной работы проверять уникальность id нужно в БД, а не в коллекции в памяти
//  2) это нужно делать в одной транзакции с обновлением БД
//  3) в БД на поле с этим id должно стоять декларативное ограничение на уникальность значений
//  но почему-то кроме как в com.golem.addCell.InsertTCommandCell#collectionID этот id больше нигде не участвует
//  или я плохо смотрел