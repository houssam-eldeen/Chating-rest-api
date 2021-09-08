
CREATE TABLE Houssam.dbo.app_user (
	id int IDENTITY(1,1) NOT NULL,
	name nvarchar(60) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	email nvarchar(60) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	password nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	created date NULL,
	comments varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL
);


INSERT INTO Houssam.dbo.app_user (name,email,password,created,comments) VALUES
	 (N'houssam',N'eng.houssam@hotmail.com',N'$2a$10$QPYOqK2QbH3QVkyzvYFKKu5W/rjXHhuFOhSGPPHoGbyiP.kRau67O',NULL,N'123456'),
	 (N'Khalid',N'khalid@abolkog.com',N'$2a$10$xyjrKo7rbXlrlx13jve0Eez5zpky41RgOXMJ4DIVe2Yyia7IapGne',NULL,N'password');