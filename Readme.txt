=====Importing Project Folder to Eclipse===
1. Open Eclipse.
2. File > Open Projects From File System > Directory > *Locate this folder* > Finish


=====Checking External Library====
1. Look for mysql-connector in referenced library inside the project folder.
2. If there's none, right-click project folder > build path > Add External Archives
3. Select the file named "mysql-connector-java-8.0.18" inside this folder.


=====Importing Database====
1. Open Xampp and go to phpMyAdmin.
2. Create database named "airlines".
3. Select the database you've created.
4. Select the "Import" tab then "Choose file"
5. Select "airlines.sql" file in this folder then Go.


====Running the Program====
1. Run "Papsi Airlines.java" inside this folder.
