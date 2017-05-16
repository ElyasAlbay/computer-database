  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admintest'@'localhost' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-test`.* TO 'admintest'@'localhost' WITH GRANT OPTION;


  FLUSH PRIVILEGES;
