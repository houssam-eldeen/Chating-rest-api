
CREATE TABLE Houssam.dbo.Todo (
	id int IDENTITY(1,1) NOT NULL,
	userId nvarchar(6) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	title nvarchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
	description nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
	[timestamp] datetime IDENTITY(1,1) NULL
);

INSERT INTO Houssam.dbo.Todo (userId,title,description,[timestamp]) VALUES
	 (N'1',N'houssam',N'des_1','2021-06-13 09:20:01.153'),
	 (N'2',N'khaled',N'des_2','2021-06-13 09:20:01.153'),
	 (N'2',N'khaled',N'des_3','2021-06-13 09:20:01.153'),
	 (N'2',N'khaled',N'des_4','2021-06-13 09:20:01.153'),
	 (N'1',N'houssam',N'des_5','2021-06-13 09:20:01.153'),
	 (N'1',N'houssam',N'des_6','2021-06-13 09:20:01.153'),
	 (N'1',N'houssam',N'des_7','2021-06-13 09:20:01.153'),
	 (N'1',N'Another Todo associated with a user',N'Saved in the db','2021-06-14 14:50:00.61');