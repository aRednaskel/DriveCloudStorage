# DriveCloudStorage
DriveCloudStorage is a project created as part of the Udacity course that allows upload/download/remove small files and add/update/remove notes and credentials.

[Authentication and authorization](https://github.com/aRednaskel/DriveCloudStorage/tree/master/src/main/java/com/projects/storage/DriveCloudStorage/config) is managed with Spring Security.

All necessary objects in [templates](https://github.com/aRednaskel/DriveCloudStorage/tree/master/src/main/resources/templates) were binded using Thymeleaf.

All objects were [mapped](https://github.com/aRednaskel/DriveCloudStorage/tree/master/src/main/java/com/projects/storage/DriveCloudStorage/mapper) using MyBatis persistence framework.

This project was [tested](https://github.com/aRednaskel/DriveCloudStorage/tree/master/src/test/java/com/projects/storage/DriveCloudStorage) using Selenium framework.
Tested functions:

```
Signing and logging a new user

Verifying that if user is not logged in, then the home page is no longer available

Tested CRUD operations for notes and credentials

Verifying if user owns notes and credentials (user can only access items, that he created)
```

