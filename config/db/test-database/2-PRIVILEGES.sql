  #-----------------------------------
  #USER RIGHTS MANAGEMENT
  #-----------------------------------
  CREATE USER 'admintest'@'%' IDENTIFIED BY 'qwerty1234';

  GRANT ALL PRIVILEGES ON `computer-database-test`.* TO 'admintest'@'%' WITH GRANT OPTION;


  FLUSH PRIVILEGES;
