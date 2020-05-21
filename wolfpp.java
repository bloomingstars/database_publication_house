import java.sql.*;
import java.util.*;
public class wolfpp {


// Update your user info alone here
private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/sdinaka"; // Using SERVICE_NAME

// Update your user and password info here!

private static final String user = "sdinaka";
private static final String password = "200316905";
static Connection connection=null;
    static Scanner s=new Scanner(System.in);
static ResultSet result=null;
private static PreparedStatement selectArticlesTopicQuery;
	private static PreparedStatement selectArticlesDateQuery;
	private static PreparedStatement selectArticlesAuthorQuery;
	private static PreparedStatement selectBooksTopicQuery;
	private static PreparedStatement selectBooksDateQuery;
	private static PreparedStatement selectBooksAuthorQuery;
	private static PreparedStatement reportCopiesPerDistributorPerMonthQuery;
    private static PreparedStatement reportTotalRevenuePerMonthQuery;
    private static PreparedStatement reportStaffExpensesPerMonthQuery;
	private static PreparedStatement reportShippingExpensesPerMonthQuery;
    private static PreparedStatement reportNumberOfDistributorsQuery;
    private static PreparedStatement reportRevenuePerCityQuery;
    private static PreparedStatement reportRevenuePerDistributorQuery;
    private static PreparedStatement reportRevenuePerLocationQuery;
    private static PreparedStatement reportTotalPaymentsPerPeriodPerTypeQuery;


    private static PreparedStatement showChaptersQuery;
 
	private static PreparedStatement addNewPublicationQuery;
    private static PreparedStatement addNewBookEditionQuery;
    private static PreparedStatement updateBookEditionISBNQuery;
    private static PreparedStatement updateBookEditionEditionQuery;
    private static PreparedStatement updateBookEditionDOPQuery;
    private static PreparedStatement deleteBookEditionQuery;
    private static PreparedStatement addPeriodicIssueQuery;
    private static PreparedStatement updatePeriodicIssueDOIQuery;
    private static PreparedStatement updatePeriodicIssuePeriodicityQuery;
    private static PreparedStatement deletePeriodicIssueQuery;
    private static PreparedStatement addArticleQuery;
    private static PreparedStatement updateArticleDOCQuery;
    private static PreparedStatement updateArticleTextQuery;
    private static PreparedStatement updateArticleTopictQuery;
    private static PreparedStatement addChapterQuery;
    private static PreparedStatement updateChapterTitleQuery;
    private static PreparedStatement updateChapterDOCQuery;
    private static PreparedStatement updateChapterTextQuery;
    private static PreparedStatement updateChapterAuthorQuery;
    private static PreparedStatement addArticleTextQuery;
    private static PreparedStatement updateArticleTopicQuery;
    private static PreparedStatement addStaffPaymentQuery;
    private static PreparedStatement updateStaffPaymentQuery;

    private static PreparedStatement addDistributorQuery;
    private static PreparedStatement updateDistributorContactpersonQuery;
    private static PreparedStatement updateDistributorPhoneQuery;
    private static PreparedStatement updateDistributorCityQuery;
    private static PreparedStatement updateDistributorAddressQuery;
    private static PreparedStatement updateDistributorTypeQuery;
    private static PreparedStatement updateDistributorNameQuery;
    private static PreparedStatement updateDistributorOweQuery;
    private static PreparedStatement deleteDistributorQuery;
    private static PreparedStatement newPublicationEntryQuery;
    private static PreparedStatement newBookEntryQuery;
	private static PreparedStatement newPeriodicIssueEntryQuery;
	private static PreparedStatement updatePublicationPriceQuery;
    private static PreparedStatement updatePublicationTitleQuery;
	private static PreparedStatement updatePublicationTopicQuery;
    private static PreparedStatement updatePublicationTypeQuery;
    private static PreparedStatement assignEditorQuery;
	private static PreparedStatement editorPublicationsViewQuery;
	private static PreparedStatement addBookChaptersQuery;
	private static PreparedStatement deletingBookChaptersQuery;
    private static PreparedStatement addArticleInPeriodicIssueQuery;
	private static PreparedStatement deletingArticleInPeriodicIssueQuery;
    private static PreparedStatement listBookEditionQuery;
    private static PreparedStatement listEditorsQuery;
    private static PreparedStatement listPeriodicIssuesQuery;
    private static PreparedStatement listAuthorsQuery;
    private static PreparedStatement listChaptersQuery;
    private static PreparedStatement listArticlesQuery;
    private static PreparedStatement prepGetLastIDPublications;
    private static PreparedStatement getnewDistIDQuery;
    private static PreparedStatement newOrderIdQuery;
    private static PreparedStatement prepGetAllDistributors;
    private static PreparedStatement prepGetAllPublications;
    private static PreparedStatement prepGetAllArtciles;

	private static PreparedStatement insertIntoOrder_TablesQuery;
    private static PreparedStatement insertIntoOrdersQuery;
    private static PreparedStatement addDistributorOweQuery;
    private static PreparedStatement insertIntoDistributorpaymentsQuery;
    private static PreparedStatement subtractDistributorOweQuery;
    private static PreparedStatement getPricePIDQuery;
    private static PreparedStatement getPIDTitleQuery;
	public static void generatePrepStatements(Connection connection){
		String sql;
		try{
		sql = "select * from Articles where Topic like ?;";
		selectArticlesTopicQuery= connection.prepareStatement(sql);
		sql = "select * from Articles where Dateofcreation like ?;";
		selectArticlesDateQuery= connection.prepareStatement(sql);
		sql = " select * from Articles where PID in (select PID from Articlewrites where SID in(select SID from Staff where Name =  ?) );";
		selectArticlesAuthorQuery= connection.prepareStatement(sql);
		sql = "select * from Books where PID in (select PID from Publications where TYPE = 'book' and TOPIC= ? );";
		selectBooksTopicQuery= connection.prepareStatement(sql);
		sql = "select * from Books where DateofPublication like ?;";
		selectBooksDateQuery= connection.prepareStatement(sql);
		sql = "select * from Books where PID in(select PID from Bookwrites where SID in (select SID from Staff where Name like ?  ));";
		selectBooksAuthorQuery= connection.prepareStatement(sql);
		sql = "INSERT INTO `Publications` (`PID`, `AMOUNT`, `TITLE`, `TOPIC`, `TYPE`) VALUES (?, ?, ?, ?, ?);";
newPublicationEntryQuery= connection.prepareStatement(sql);

sql = "INSERT INTO `Publications` (`PID`, `ISBN`, `Edition`, `DateOfPublication`) VALUES (?, ?, ?, ?);";
newBookEntryQuery= connection.prepareStatement(sql);

sql = "INSERT INTO `Publications` (`PID`, `DateofIssue`, `Periodicity`) VALUES (?, ?, ?);";
newPeriodicIssueEntryQuery= connection.prepareStatement(sql);

sql = "UPDATE `Publications`" + " SET `AMOUNT` = ? WHERE PID= ?;";
updatePublicationPriceQuery= connection.prepareStatement(sql);

sql = "UPDATE `Publications`" + " SET `TITLE` = ? WHERE PID= ?;";
updatePublicationTitleQuery= connection.prepareStatement(sql);

sql = "UPDATE `Publications`" + " SET `TOPIC` = ? WHERE PID= ?;";
updatePublicationTopicQuery= connection.prepareStatement(sql);

sql = "UPDATE `Publications`" + " SET `TYPE` = ? WHERE PID= ?;";
updatePublicationTypeQuery= connection.prepareStatement(sql);

sql = "INSERT INTO `Edits` (`PID`, `SID`) VALUES (?, ?);";
assignEditorQuery= connection.prepareStatement(sql);
sql = "select * FROM `Publications` WHERE PID IN (SELECT PID FROM Edits WHERE SID = ?);";
editorPublicationsViewQuery= connection.prepareStatement(sql);

sql = "INSERT INTO `Chapters` (`PID`, `Title`, `Text`, `Dateofcreation`) VALUES (?, ?, ?, ?);";
addBookChaptersQuery= connection.prepareStatement(sql);


sql = "DELETE FROM `Chapters`" + " WHERE PID = ? and Title= ?;";
deletingBookChaptersQuery= connection.prepareStatement(sql);

sql = "INSERT INTO `Articles` (`PID`, `Title`, `Text`, `Dateofcreation`,`Topic`) VALUES (?, ?, ?, ?,?);";
addArticleInPeriodicIssueQuery= connection.prepareStatement(sql);

sql = "DELETE FROM `Articles`" + " WHERE PID = ? and Title =?;";
deletingArticleInPeriodicIssueQuery= connection.prepareStatement(sql);
    /*************************************************************************************************** */

    sql = "Select * From `Chapters` where PID=? ;";
    showChaptersQuery= connection.prepareStatement(sql);

	sql = "INSERT INTO `Books` (`PID`, `ISBN`,`Edition`,`DateofPublication`) VALUES (?,?,?,?);";
    addNewBookEditionQuery= connection.prepareStatement(sql);


    sql = "INSERT INTO `Publications` (`PID`, `AMOUNT`,`TITLE`,`TOPIC`, `TYPE`) VALUES (?,?,?,?,?);";
    addNewPublicationQuery= connection.prepareStatement(sql);


    sql = "UPDATE `Books`" + " SET `ISBN` = ? WHERE `PID`= ?;";
    updateBookEditionISBNQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Books`" + " SET `Edition` = ? WHERE `PID`= ?;";
    updateBookEditionEditionQuery  = connection.prepareStatement(sql);

    sql = "UPDATE `Books`" + " SET `DateofPublication` = ? WHERE `PID`= ?;";
    updateBookEditionDOPQuery= connection.prepareStatement(sql);


    sql = "DELETE FROM `Publications`" + " WHERE `PID` = ?;"; 
    deleteBookEditionQuery= connection.prepareStatement(sql);

	
	
    sql = "INSERT INTO `Periodicissues` (`PID`, `Dateofissue`,`Periodicity`) VALUES (?,?,?);";
    addPeriodicIssueQuery= connection.prepareStatement(sql);


    sql = "UPDATE `Periodicissues`" + " SET `Dateofissue` = ? WHERE `PID`= ?;";
    updatePeriodicIssueDOIQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Periodicissues`" + " SET `Periodicity` = ? WHERE `PID`= ?;";
    updatePeriodicIssuePeriodicityQuery  = connection.prepareStatement(sql);


    sql = "DELETE FROM `Publications`" + " WHERE `PID` = ?;"; 
    deletePeriodicIssueQuery= connection.prepareStatement(sql);


    sql = "INSERT INTO `Articles` (`PID`, `title`,`Dateofcreation`,`Text`,`Topic`) VALUES (?,?,?,?,?);";
    addArticleQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Articles`" + " SET `Dateofcreation` = ? WHERE `PID`= ? and Title =?;";
    updateArticleDOCQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Articles`" + " SET `text` = ? WHERE `PID`= ? and Title = ?;";
    updateArticleTextQuery  = connection.prepareStatement(sql);

    sql = "UPDATE `Articles`" + " SET `Topic` = ? WHERE `PID`= ? and Title = ?;";
    updateArticleTopicQuery  = connection.prepareStatement(sql);

    sql = "INSERT INTO `Chapters` (`PID`, `Title`,`Text`, `Dateofcreation`) VALUES (?,?,?,?);";
    addChapterQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Chapters` SET `Title` = ? WHERE `PID`= ? AND `Title`=?;";
    updateChapterTitleQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Chapters` SET `Dateofcreation` = ? WHERE `PID`= ? AND `Title`=?;";
    updateChapterDOCQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Chapters` SET `Text` = ? WHERE `PID`= ? AND `Title`=?;";
    updateChapterTextQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Bookwrites` SET `SID` = ? WHERE `PID`= ?;";
    updateChapterAuthorQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Articles` SET `text` = ? WHERE `PID`= ? and Title=?;";
    addArticleTextQuery= connection.prepareStatement(sql);

    sql = "INSERT INTO `StaffPayments` (`SID`, `Salarydate`,`amount`, `Dateofcollection`) VALUES (?,?,?,?);";
    addStaffPaymentQuery= connection.prepareStatement(sql);

   sql = "UPDATE `StaffPayments` SET `Dateofcollection` = ? WHERe `SID` = ? AND `Salarydate`=?;";
    updateStaffPaymentQuery= connection.prepareStatement(sql);

	/************************************************************************************************** */
	sql = "SELECT DAccnumber, PID, sum(ordervalue) as 'ordervalue',sum(noofcopies) as 'noofcopies' FROM `Order_tables` NATURAL JOIN `Orders` WHERE orderdate like ?  GROUP BY DAccnumber,pid";
	reportCopiesPerDistributorPerMonthQuery = connection.prepareStatement(sql);

	sql = "SELECT sum(Amountpaid) as 'amountpaid' FROM `Distributorpayments` WHERE paymentdate like ?";
    reportTotalRevenuePerMonthQuery = connection.prepareStatement(sql);

	sql="SELECT sum(amount) as 'amount' from StaffPayments WHERE Salarydate like ?";
	reportStaffExpensesPerMonthQuery=connection.prepareStatement(sql);
    sql= "select sum(shippingcost) as 'amountpaid' from Order_tables WHERE orderduedate like ?";
    reportShippingExpensesPerMonthQuery=connection.prepareStatement(sql);
       
    sql = "SELECT count(DAccnumber) as 'noofdistributors' FROM `Distributors`;";
    reportNumberOfDistributorsQuery = connection.prepareStatement(sql);

    sql = "SELECT city,sum(Amountpaid) as 'amountpaid' FROM `Distributorpayments` NATURAL JOIN `Distributors` group by city;";
    reportRevenuePerCityQuery = connection.prepareStatement(sql);

    sql = "SELECT DAccnumber,sum(Amountpaid) as 'amountpaid' FROM `Distributorpayments` NATURAL JOIN `Distributors` group by DAccnumber;";
    reportRevenuePerDistributorQuery = connection.prepareStatement(sql);

 
   sql = "SELECT City,Staddress,sum(Amountpaid) as 'amountpaid' FROM `Distributorpayments` NATURAL JOIN `Distributors` group by City,Staddress;";
    reportRevenuePerLocationQuery = connection.prepareStatement(sql);

    sql = "SELECT JobRole as jobrole,sum(amount) as 'amount' FROM `StaffPayments` natural join Staff where Salarydate LIKE ? group by JobRole;";
    reportTotalPaymentsPerPeriodPerTypeQuery = connection.prepareStatement(sql);



	/********************************************************************************************************* */

    sql = "INSERT INTO `Distributors` (`DAccnumber`, `Contactperson`,`Phone`, `City`, `Staddress`, `type`,`distributorname`,`owe`) VALUES (?,?,?,?,?,?,?,?);";
    addDistributorQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `Contactperson` = ? WHERE `DAccnumber`= ?;";
    updateDistributorContactpersonQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `Phone` = ? WHERE `DAccnumber`= ?;";
    updateDistributorPhoneQuery  = connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `City` = ? WHERE `DAccnumber`= ?;";
    updateDistributorCityQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `Staddress` = ? WHERE `DAccnumber`= ?;";
    updateDistributorAddressQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `type` = ? WHERE `DAccnumber`= ?;";
    updateDistributorTypeQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `distributorname` = ? WHERE `DAccnumber`= ?;";
    updateDistributorNameQuery= connection.prepareStatement(sql);

    sql = "UPDATE `Distributors`" + " SET `owe` = ? WHERE `DAccnumber`= ?;";
    updateDistributorOweQuery= connection.prepareStatement(sql);

    sql = "DELETE FROM `Distributors`" + " WHERE `DAccnumber` = ?;"; 
    deleteDistributorQuery= connection.prepareStatement(sql);
    
    sql = "SELECT Title as 'title' FROM `Publications` WHERE PID = ?;";
    getPIDTitleQuery = connection.prepareStatement(sql);

    sql = "SELECT Amount as 'price' FROM `Publications` WHERE PID = ?;";
    getPricePIDQuery = connection.prepareStatement(sql);

    sql = "INSERT INTO `Order_tables` (`Orderno`, `noofcopies`, `orderdate`, `orderduedate`, `ordervalue`, `shippingcost`) VALUES (?, ?, ?, ?, ?,?);";
    insertIntoOrder_TablesQuery = connection.prepareStatement(sql);

    sql = "INSERT INTO `Orders` (`PID`, `DAccnumber`, `Orderno`, `Title`) VALUES (?, ?, ?, ?);";
    insertIntoOrdersQuery = connection.prepareStatement(sql);
    
    sql = "INSERT INTO `Distributorpayments` (`DAccnumber`, `Paymentdate`, `Amountpaid`) VALUES (?, ?, ?);";
    insertIntoDistributorpaymentsQuery = connection.prepareStatement(sql);

    sql = "UPDATE `Distributors` set owe=owe+? WHERE DAccnumber=?;";
    addDistributorOweQuery = connection.prepareStatement(sql);

    sql = "UPDATE `Distributors` set owe=owe-? WHERE DAccnumber=?;";
    subtractDistributorOweQuery = connection.prepareStatement(sql);

    /***************************************************************************************** */
    sql= "select * from Books natural join Publications;";
    listBookEditionQuery=connection.prepareStatement(sql);

    sql= "select name, sid, JobRole from Staff where JobRole='editor';";
    listEditorsQuery=connection.prepareStatement(sql);

    sql="select * from Articles;";
    listArticlesQuery=connection.prepareStatement(sql);

    sql="select * from Periodicissues natural join Publications;";
	listPeriodicIssuesQuery=connection.prepareStatement(sql);

    sql="select SID,name,JobRole from Staff WHERE JobRole='author';";
    listAuthorsQuery=connection.prepareStatement(sql);

    sql="select * from Chapters where PID = ?;";
    listChaptersQuery=connection.prepareStatement(sql);


    sql = "select PID from Publications;";
    prepGetLastIDPublications= connection.prepareStatement(sql);
    
    sql = "select DAccnumber from Distributors;";
    getnewDistIDQuery= connection.prepareStatement(sql);
    
    sql = "select Orderno from Order_tables;";
    newOrderIdQuery= connection.prepareStatement(sql);
    
    sql = "select * from Distributors;";
    prepGetAllDistributors= connection.prepareStatement(sql);
    
    sql = "select * from Publications;";
    prepGetAllPublications = connection.prepareStatement(sql);

    sql = "select * from Articles where PID=?;";
    prepGetAllArtciles = connection.prepareStatement(sql);

		}
		catch (SQLException e) {
                        e.printStackTrace();
                }
	}


/**********************************************************************************************************************************/
//Editing and publishing sql

    public static void addNewPublication_sql(int PID,int price,String Title,String Topic,String Type)
    {
        try {
              connection.setAutoCommit(false);
            try {
                    addNewPublicationQuery.setInt(1,PID);
                    addNewPublicationQuery.setString(3,Title);
                    addNewPublicationQuery.setInt(2, price);
                    addNewPublicationQuery.setString(5, Type);
                    addNewPublicationQuery.setString(4, Topic);
                    addNewPublicationQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void  addNewBookEdition_sql(int PID,int isbn,int edition, String dop)
    {
        try {
              connection.setAutoCommit(false);
            try {
                    addNewBookEditionQuery.setInt(1,PID);
                    addNewBookEditionQuery.setInt(2,isbn);
                    addNewBookEditionQuery.setInt(3, edition);
                    addNewBookEditionQuery.setString(4, dop);
                    addNewBookEditionQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void  addPeriodicIssue_sql(int PID, String doi, String periodicity)
    { 
        try {
              connection.setAutoCommit(false);
            try {
                    addPeriodicIssueQuery.setInt(1,PID);
                    addPeriodicIssueQuery.setString(2,doi);
                    addPeriodicIssueQuery.setString(3, periodicity);
                    addPeriodicIssueQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    


	public static void updatePeriodicIssue_sql(int PID,String attribute,String newValue){
        try {
            connection.setAutoCommit(false);
            try {
                switch (attribute) {

                case "1":
                    updatePeriodicIssueDOIQuery.setString(1, newValue );
                    updatePeriodicIssueDOIQuery.setInt(2, PID);
                    updatePeriodicIssueDOIQuery.executeUpdate();
                    break;
                case "2":
                    updatePeriodicIssuePeriodicityQuery.setString(1, newValue);
                    updatePeriodicIssuePeriodicityQuery.setInt(2, PID);
                    updatePeriodicIssuePeriodicityQuery.executeUpdate();
                    break;
                default:
                    System.out.println("Cannot update the field " + attribute + " for Periodic Issue" + PID + " .");
                    break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
    }

    public static void editorPublicationsView_sql(int SID) {
		try {
			connection.setAutoCommit(false);
			try {
				editorPublicationsViewQuery.setInt(1, SID);
				result= editorPublicationsViewQuery.executeQuery();

				result.beforeFirst();
			while (result.next()) {
				System.out.print("\n PID: " + result.getString("PID") + " | ");
				System.out.print("amount: " + result.getString("amount") + " | ");
				System.out.print("title: " + result.getString("title") + " | ");
				System.out.print("topic: " + result.getString("topic") + " | ");
				System.out.print("type: " + result.getString("type") + " \n ");
			}
			 System.out.println();

				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }


 public static void addBookChapters_sql (int PID,String Title,String Text,String DOC) {
		try {
			connection.setAutoCommit(false);
			try {
				addBookChaptersQuery.setInt(1, PID);
				addBookChaptersQuery.setString(2, Title);
				addBookChaptersQuery.setString(3,Text);
				addBookChaptersQuery.setString(4, DOC);
				addBookChaptersQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

public static void listStaff(){
}
public static void deletingBookChapters_sql (int PID,String Title) {
		try {
			connection.setAutoCommit(false);
			try {
				deletingBookChaptersQuery.setInt(1, PID);
				deletingBookChaptersQuery.setString(2,Title);
				deletingBookChaptersQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
}

public static void addArticleInPeriodicIssue_sql (int  PID, String Title,String Text,String DateofCreation,String Topic) {
		try {
			connection.setAutoCommit(false);
			try {
				addArticleInPeriodicIssueQuery.setInt(1, PID);
				addArticleInPeriodicIssueQuery.setString(2,Title);
				addArticleInPeriodicIssueQuery.setString(3,Text);
				addArticleInPeriodicIssueQuery.setString(4,DateofCreation);
				addArticleInPeriodicIssueQuery.setString(5,Topic);
				addArticleInPeriodicIssueQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

}


public static void deletingArticleInPeriodicIssue_sql (int PID,String Title) {
		try {
			connection.setAutoCommit(false);
			try {
				deletingArticleInPeriodicIssueQuery.setInt(1, PID);
				deletingArticleInPeriodicIssueQuery.setString(2,Title);
				deletingArticleInPeriodicIssueQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
}



/*********************************************************************************************************************/

//Production sql
	public static void updatePublicationInfo_sql(int PID,String attributechanged, String newattrvalue)    {
		try {
			connection.setAutoCommit(true);
			try {
				switch (attributechanged) {

				case "1":
					updatePublicationPriceQuery.setString(1, newattrvalue);
					updatePublicationPriceQuery.setInt(2, PID);
					updatePublicationPriceQuery.executeUpdate();
					break;
				case "2":
					updatePublicationTitleQuery.setString(1, newattrvalue);
					updatePublicationTitleQuery.setInt(2, PID);
					updatePublicationTitleQuery.executeUpdate();
					break;
				case "3":
					updatePublicationTopicQuery.setString(1, newattrvalue);
					updatePublicationTopicQuery.setInt(2, PID);
					updatePublicationTopicQuery.executeUpdate();
					break;
				case "4":
					updatePublicationTypeQuery.setString(1, newattrvalue);
					updatePublicationTypeQuery.setInt(2, PID);
					updatePublicationTypeQuery.executeUpdate();
					break;

				default:
					System.out.println("Cannot update the field " + attributechanged + " for id" + PID+ " .");
					break;
				}
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void updateBookEdition_sql(int PID,String attribute,String newValue){
        try {
            connection.setAutoCommit(false);
            try {
                switch (attribute) {

                case "1":
                    updateBookEditionISBNQuery.setString(1, newValue );
                    updateBookEditionISBNQuery.setInt(2, PID);
                    updateBookEditionISBNQuery.executeUpdate();
                    break;
                case "2":
                    updateBookEditionEditionQuery.setString(1, newValue);
                    updateBookEditionEditionQuery.setInt(2, PID);
                    updateBookEditionEditionQuery.executeUpdate();
                    break;
                case "3":
                    updateBookEditionDOPQuery.setString(1, newValue);
                    updateBookEditionDOPQuery.setInt(2, PID);
                    updateBookEditionDOPQuery.executeUpdate();
                    break;
                default:
                    System.out.println("Cannot update the field " + attribute + " for Book" + PID + " .");
                    break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }	

      public static void assignEditor_sql (int  PID, int SID) {
		try {
			connection.setAutoCommit(false);
			try {
				assignEditorQuery.setInt(1, PID);
assignEditorQuery.setInt(2,SID);
				assignEditorQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void deleteBookEdition_sql(int PID){
        try {
            connection.setAutoCommit(false);
            try {
                deleteBookEditionQuery.setInt(1, PID);
                deleteBookEditionQuery.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public static void deletePeriodicIssue_sql(int PID){
        try {
            connection.setAutoCommit(false);
            try {
                deletePeriodicIssueQuery.setInt(1, PID);
                deletePeriodicIssueQuery.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
   }

   public static void addArticle_sql(int PID,String title, String DOC, String text, String topic){
        try {
              connection.setAutoCommit(false);
            try {
                    addArticleQuery.setInt(1,PID);
                    addArticleQuery.setString(2,title);
                    addArticleQuery.setString(3, DOC);
                    addArticleQuery.setString(4,text);
                    addArticleQuery.setString(5, topic);
                    addArticleQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
    }

    public static void addChapter_sql(int PID,String title,String text,String DOC){
        try {
              connection.setAutoCommit(false);
            try {
                    addChapterQuery.setInt(1,PID);
                    addChapterQuery.setString(2,title);
                    addChapterQuery.setString(3, text);
                    addChapterQuery.setString(4,DOC);
                    addChapterQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
	}
    

    public static void addArticleText_sql(int PID,String title, String text){
        try {
              connection.setAutoCommit(false);
            try {
                    addArticleTextQuery.setInt(1,PID);
                    addArticleTextQuery.setString(2,title);
                    addArticleTextQuery.setString(3, text);
                    addArticleTextQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
	}

	public static void updateArticle_sql(int PID,String title,String attribute,String newValue){
        try {
            connection.setAutoCommit(false);
            try {
                switch (attribute) {

                case "1":
                    updateArticleDOCQuery.setString(1, newValue );
                    updateArticleDOCQuery.setInt(2, PID);
                    updateArticleDOCQuery.setString(3, title);
                    updateArticleDOCQuery.executeUpdate();
                    break;
                case "2":
                    updateArticleTextQuery.setString(1, newValue);
                    updateArticleTextQuery.setInt(2, PID);
                    updateArticleTextQuery.setString(3, title);
                    updateArticleTextQuery.executeUpdate();
                    break;
                case "3":
                    updateArticleTopicQuery.setString(1, newValue);
                    updateArticleTopicQuery.setInt(2, PID);
                    updateArticleTopicQuery.setString(3, title);
                    updateArticleTopicQuery.executeUpdate();
                    break;
                default:
                    System.out.println("Cannot update the field " + attribute + " for Article" + PID + " .");
                    break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

	public static void updateChapter_sql(int PID,String title,String attribute,String newValue){
        try {
            connection.setAutoCommit(false);
            try {
                switch (attribute) {

                case "1":
                    updateChapterTitleQuery.setString(1, newValue );
                    updateChapterTitleQuery.setInt(2, PID);
                    updateChapterTitleQuery.setString(3,title );
                    updateChapterTitleQuery.executeUpdate();
                    break;
                case "2":
                    updateChapterDOCQuery.setString(1, newValue);
                    updateChapterDOCQuery.setInt(2, PID);
                    updateChapterDOCQuery.setString(3, title);
                    updateChapterDOCQuery.executeUpdate();
                    break;
                case "3":
                    updateChapterTextQuery.setString(1, newValue);
                    updateChapterTextQuery.setInt(2, PID);
                    updateChapterTextQuery.setString(3, title);
                    updateChapterTextQuery.executeUpdate();
                    break;
                case "4":
                    updateChapterAuthorQuery.setString(1, newValue);
                    updateChapterAuthorQuery.setInt(2, PID);
                    //updateChapterAuthorQuery.setString(3, title);
                    updateChapterAuthorQuery.executeUpdate();
                    break;
                default:
                    System.out.println("Cannot update the field " + attribute + " for Chapter" + PID + " .");
                    break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
         
    }

	public static void updateArticleText_sql(int PID,String title, String text){
         try {
            connection.setAutoCommit(false);
            try {
                    updateArticleTextQuery.setString(1, text );
                    updateArticleTextQuery.setInt(2, PID);
                    updateArticleTextQuery.setString(3, title);
                    updateArticleTextQuery.executeUpdate();
                   
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
     
    }

	public static void addStaffPayment_sql(final int SID, final String DOP, final int amount, final String DOC) {
        try {
            connection.setAutoCommit(false);
            try {
                addStaffPaymentQuery.setInt(1, SID);
                addStaffPaymentQuery.setString(2, DOP);
                addStaffPaymentQuery.setInt(3, amount);
                addStaffPaymentQuery.setString(4, DOC);
                addStaffPaymentQuery.executeUpdate();
                connection.commit();
            } catch (final SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }
    
	public static void findBooks_sql(String attribute, String attr_val){
		try {
			listBooks();
			ResultSet result=null;
			connection.setAutoCommit(true);
			try {
				switch (attribute) {

				case "1":
					selectBooksTopicQuery.setString(1, attr_val);
					result = selectBooksTopicQuery.executeQuery();
					break;
				case "2":
					selectBooksDateQuery.setString(1, attr_val);
					result = selectBooksDateQuery.executeQuery();
					break;
				case "3":
					selectBooksAuthorQuery.setString(1, attr_val);
					result = selectBooksAuthorQuery.executeQuery();
					break;

				default:
					System.out.println("Cannot fetch from field " + attribute );
					break;
				}
				if(result!=null){
					result.beforeFirst();
					System.out.println("\nshowTestRecord\n");
					while (result.next()) {
						System.out.print("\t PID: " + result.getString("PID") + " | ");
						System.out.print("ISBN: " + result.getString("ISBN") + " | ");
						System.out.print("Edition : " + result.getString("Edition") + " | ");
						System.out.print(" Date of publication : " + result.getString("DateofPublication") + " \n ");
					}
				}

				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
	public static void findArticle_sql(String attribute, String attr_val){
		try {
			listArticles();
			ResultSet result=null;
			connection.setAutoCommit(true);
			try {
				switch (attribute) {

				case "1":
					selectArticlesTopicQuery.setString(1, attr_val);
					result = selectArticlesTopicQuery.executeQuery();
					break;
				case "2":
					selectArticlesDateQuery.setString(1, attr_val);
					result = selectArticlesDateQuery.executeQuery();
					break;
				case "3":
					selectArticlesAuthorQuery.setString(1, attr_val);
					result = selectArticlesAuthorQuery.executeQuery();
					break;

				default:
					System.out.println("Cannot fetch from field " + attribute );
					break;
				}
				if(result!=null){
					result.beforeFirst();
					System.out.println("\nshowTestRecord\n");
					while (result.next()) {
						System.out.print("\t PID: " + result.getString("PID") + " | ");
						System.out.print("title: " + result.getString("title") + " | ");
						System.out.print("text : " + result.getString("text") + " | ");
						System.out.print(" Date of creation : " + result.getString("Dateofcreation") + " | ");
						System.out.print("Topic: " + result.getString("Topic") + " \n ");

					}
				}

				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*********************************************************************************************************************** */


	//helper functions
	/*********************************************************************************************************************** */

	//list helpers
    public static void listBookEdition(){
        System.out.println("Listing Available Books");
        try{
			result = listBookEditionQuery.executeQuery();
			result.beforeFirst();
			while (result.next()) {
				System.out.print(" PID: " + result.getInt("PID") + " | ");
				System.out.print("ISBN: " + result.getInt("ISBN") + " | ");
				System.out.print("Edition: " + result.getInt("Edition") + " | ");
				System.out.print("DateofPublication: " + result.getString("DateofPublication") + " | ");
                System.out.print("AMOUNT: " + result.getString("AMOUNT") + " | ");
                System.out.print("TITLE: " + result.getString("TITLE") + " | ");
                System.out.print("TOPIC: " + result.getString("TOPIC") + " | ");
                System.out.print("TYPE: " + result.getString("TYPE") + " | \n");
			}
			
		} catch (Throwable err) {
			err.printStackTrace();
		}
		
	}


    public static void listEditors(){
        System.out.println("Listing All the Editors");
        try{
			result = listEditorsQuery.executeQuery();
		    result.beforeFirst();
			while (result.next()) {
                System.out.print("name: " + result.getString("Name") + " | ");
                System.out.print(" Editor id: " + result.getString("SID") + " | ");
                System.out.print("JobRole: " + result.getString("JobRole") + " |\n ");
			}
			
		} catch (Throwable err) {
			err.printStackTrace();
		}
    }

    public static void listArticles(){
        System.out.println("Listing Available Articles");
        try{
			result = listArticlesQuery.executeQuery();
		    result.beforeFirst();
			while (result.next()) {
				System.out.print(" PID: " + result.getInt("PID") + " | ");
				System.out.print("title: " + result.getString("title") + " | ");
				System.out.print("text: " + result.getString("text") + " | ");
                System.out.print("Dateofcreation: " + result.getString("Dateofcreation") + " | ");
                System.out.print("Topic: " + result.getString("Topic") + " |\n ");
			}
			
		} catch (Throwable err) {
			err.printStackTrace();
		}
    }

    public static void listPeriodicIssues(){
        System.out.println("Listing Available Periodic Issues");
        try{
            			
            result = listPeriodicIssuesQuery.executeQuery();
			result.beforeFirst();
			while (result.next()) {
				System.out.print(" PID: " + result.getInt("PID") + " | ");
				System.out.print("Dateofissue: " + result.getString("Dateofissue") + " | ");
				System.out.print("Periodicity: " + result.getString("Periodicity") + " | ");
                System.out.print("AMOUNT: " + result.getString("AMOUNT") + " | ");
                System.out.print("TITLE: " + result.getString("TITLE") + " | ");
                System.out.print("TOPIC: " + result.getString("TOPIC") + " | ");
                System.out.print("TYPE: " + result.getString("TYPE") + " | \n");
			}
			
		} catch (Throwable err) {
			err.printStackTrace();
		}
		
	} 

    public static void listAuthors(){
        System.out.println("Listing Authors");
        try{
			result = listAuthorsQuery.executeQuery();
		    result.beforeFirst();
			while (result.next()) {
                System.out.print("SID: " + result.getString("SID") + " | ");
                System.out.print(" name: " + result.getString("name") + " | ");
                System.out.print("JobRole: " + result.getString("JobRole") + " |\n ");
			}
			
		} catch (Throwable err) {
			err.printStackTrace();
		}
    }
	public static void listBooks(){
        listBookEdition();
	}

	public static void listChapters(int PID){
        try{
            listChaptersQuery.setInt(1,PID);
	        result = listChaptersQuery.executeQuery();
	        result.beforeFirst();
            while (result.next()) {
		        System.out.print(" PID: " + result.getString("PID") + " | ");
		        System.out.println("Title: " + result.getString("Title"));
		        System.out.print("Text: " + result.getString("Text") + " |");
		        System.out.print("Date of Creation: " + result.getString("Dateofcreation") );

	        }
        }
    catch (Throwable err) {
			err.printStackTrace();
		}
	
	}

public static int newPublicationsId(){
    int id=0;
	try{
	result = prepGetLastIDPublications.executeQuery();
	result.beforeFirst();
	while (result.next()) {
		id= result.getInt("PID");
	}
}
catch (SQLException e) {
    e.printStackTrace();}
return id+1;
}

public static int getnewDistID(){
int id=0; 
  try{
	result = getnewDistIDQuery.executeQuery();
	result.beforeFirst();
	while (result.next()) {
		id= result.getInt("DAccnumber");
    }
    }

    catch (SQLException e) {
        e.printStackTrace();
}
return id+1;

}

public static int newOrderId(){
   int id=0;
	try{
	result = newOrderIdQuery.executeQuery();
	result.beforeFirst();
	while (result.next()) {
		id= result.getInt("Orderno");
	}
    }
    catch (SQLException e) {
        e.printStackTrace();
}
return id+1;

}


public static void listDistributors(){
	
            try {
                  connection.setAutoCommit(false);
                try {
                    result = prepGetAllDistributors.executeQuery();
                    result.beforeFirst();
                    while (result.next()) {
                        System.out.print(" DAccnumber: " + result.getInt("DAccnumber") + " | ");
                        
                        System.out.println("Contact person: " + result.getString("Contactperson")+"|");
                        System.out.print("Phone: " + result.getString("Phone")+"|" );
                        System.out.print("City: " + result.getString("City") +"|");
                        System.out.print("Phone: " + result.getString("Staddress")+"|" );
                        System.out.print("Type: " + result.getString("type")+"|" );
                        System.out.print("Distributorname: " + result.getString("distributorname") +"|");
                        System.out.print("Owe: " + result.getInt("Owe")+"\n" );
                } }catch (SQLException e) {
                    connection.rollback();
                    e.printStackTrace();
                } finally {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	

public static void listPublications(){

    try {
        connection.setAutoCommit(false);
      try {
        result = prepGetAllPublications.executeQuery();
        result.beforeFirst();
        while (result.next()) {
            System.out.print(" PID: " + result.getInt("PID") + " | ");
            System.out.print("Price: " + result.getInt("AMOUNT") + " |");
            System.out.println("Title: " + result.getString("TITLE")+"|");
            System.out.println("Topic: " + result.getString("TOPIC")+"|");
            System.out.println("Type: " + result.getString("TYPE")+"\n");
      }} catch (SQLException e) {
          connection.rollback();
          e.printStackTrace();
      } finally {
          connection.setAutoCommit(true);
      }
  } catch (SQLException e) {
      e.printStackTrace();
  }
}
public static void listArticlesOfPublication(int PID){
    
    try {
        connection.setAutoCommit(false);
      try {
        prepGetAllArtciles.setInt(1,PID);
	result = prepGetAllArtciles.executeQuery();
	result.beforeFirst();
	while (result.next()) {
		System.out.print(" PID: " + result.getInt("PID") + " | ");
		System.out.println("Title: " + result.getString("title"));
		System.out.print("Text: " + result.getString("text") + " |");
        System.out.print("Date of Creation: " + result.getString("Dateofcreation")+"|" );
        System.out.print("Topic: " + result.getString("Topic")+"\n" );
      }} catch (SQLException e) {
          connection.rollback();
          e.printStackTrace();
      } finally {
          connection.setAutoCommit(true);
      }
  } catch (SQLException e) {
      e.printStackTrace();
  }

}
    

    public static void updateStaffPayment_sql(final int SID, final String DOP,final String DOC) {
        try {
            connection.setAutoCommit(false);
            try {
                updateStaffPaymentQuery.setString(1, DOC);
                updateStaffPaymentQuery.setInt(2, SID);
                updateStaffPaymentQuery.setString(3, DOP);
                updateStaffPaymentQuery.executeUpdate();
                connection.commit();
            } catch (final SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        }
    }


	/****************************************************************************************************************************** */
	//distributor sql


    


    public static void addDistributor_sql(int daccnumber, String contactperson, String phone, String city , String address,
        String disttype, String distname, String owe) {
        try {
              connection.setAutoCommit(false);
            try {
                    addDistributorQuery.setInt(1,daccnumber);
                    addDistributorQuery.setString(2, contactperson);
                    addDistributorQuery.setString(3, phone);
                    addDistributorQuery.setString(4, city);
                    addDistributorQuery.setString(5, address);
                    addDistributorQuery.setString(6, disttype);
                    addDistributorQuery.setString(7, distname);
                    addDistributorQuery.setString(8, owe);
                    addDistributorQuery.executeUpdate();
                    connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.print("\n Adding Distributor in sqlt\n");
    } 

    public static void updateDistributor(){
            try {
                    listDistributors();
                    System.out.println("Enter the Distributor Account number" );
                    int dAccnumber = s.nextInt();
		s.nextLine();
                    System.out.println("Select the attribute you want to update" );
                    System.out.println("1 - " );
                    System.out.println("\t-- Contactperson");
                    System.out.println("2 - " );
                    System.out.println("\t-- phone");
                    System.out.println("3 - ");
                    System.out.println("\t-- city");
                    System.out.println("4 - " );
                    System.out.println("\t-- Street Address");
                    System.out.println("5 - " );
                    System.out.println("\t-- Distributor type");
                    System.out.println("6 - " );
                    System.out.println("\t-- Distributor name");
                    System.out.println("7 - ");
                    System.out.println("\t-- - The amount distributor owes");
                    String attribute =s.nextLine();
                    System.out.println("Enter the new value");
                    String newValue = s.nextLine();
                    updateDistributor_sql(dAccnumber,attribute,newValue); 
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }

    public static void updateDistributor_sql(int daccnumber, String attribute, String newValue) {
        try {
            connection.setAutoCommit(false);
            try {
                switch (attribute) {

                case "1":
                    updateDistributorContactpersonQuery.setString(1, newValue);
                    updateDistributorContactpersonQuery.setInt(2, daccnumber);
                    updateDistributorContactpersonQuery.executeUpdate();
                    break;
                case "2":
                    updateDistributorPhoneQuery.setString(1, newValue);
                    updateDistributorPhoneQuery.setInt(2, daccnumber);
                    updateDistributorPhoneQuery.executeUpdate();
                    break;
                case "3":
                    updateDistributorCityQuery.setString(1, newValue);
                    updateDistributorCityQuery.setInt(2, daccnumber);
                    updateDistributorCityQuery.executeUpdate();
                    break;
                case "4":
                    updateDistributorAddressQuery.setString(1, newValue);
                    updateDistributorAddressQuery.setInt(2, daccnumber);
                    updateDistributorAddressQuery.executeUpdate();
                    break;
                case "5":
                    updateDistributorTypeQuery.setString(1, newValue);
                    updateDistributorTypeQuery.setInt(2, daccnumber);
                    updateDistributorTypeQuery.executeUpdate();
                   break;
                case "6":
                    updateDistributorNameQuery.setString(1, newValue);
                    updateDistributorNameQuery.setInt(2, daccnumber);
                    updateDistributorNameQuery.executeUpdate();
                   break;
                case "7":
                    updateDistributorOweQuery.setString(1, newValue);
                    updateDistributorOweQuery.setInt(2, daccnumber);
                    updateDistributorOweQuery.executeUpdate();
                    break;
                default:
                    System.out.println("Cannot update the field " + attribute + " for Distributor " + daccnumber + " .");
                    break;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    } 

    public static void deleteDistributor(){
        // Declare local variables
        try {
            // Get staff id
            listDistributors();
            System.out.println("\nEnter the Distributor Account Number you want to delete:\n");
            int dAccnumber = s.nextInt();
	s.nextLine();	
            // Call method that interacts with the Database
            deleteDistributor_sql(dAccnumber);
            System.out.println("The Distributor is deleted successfully!");
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }

        // delete an appointed staff
    public static void deleteDistributor_sql(int dAccnumber) {
        try {
            connection.setAutoCommit(false);
            try {
                deleteDistributorQuery.setInt(1, dAccnumber);
                deleteDistributorQuery.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 


	public static String getPIDTitle_sql(int PID){
        try {
            getPIDTitleQuery.setInt(1,PID);
            
			result = getPIDTitleQuery.executeQuery();

			result.beforeFirst();
			while (result.next()) {
				return result.getString("title");
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		return "";
    }
    public static int getPricePID_sql(int PID){
        try {
            getPricePIDQuery.setInt(1,PID);
            
			result = getPricePIDQuery.executeQuery();

			result.beforeFirst();
			while (result.next()) {
				return result.getInt("price");
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		return 0;
     }
    public static void insertIntoOrder_Tables_sql(int OID,int noc,String order_date,String order_due_date,int order_value,int shipping_cost){
        try {
			connection.setAutoCommit(false);
			try {
			insertIntoOrder_TablesQuery.setInt(1, OID);
            insertIntoOrder_TablesQuery.setInt(2, noc);
            insertIntoOrder_TablesQuery.setString(3, order_date);
            insertIntoOrder_TablesQuery.setString(4, order_due_date);
            insertIntoOrder_TablesQuery.setInt(5, order_value);
            insertIntoOrder_TablesQuery.setInt(6, shipping_cost);
            insertIntoOrder_TablesQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}
    
    public static void insertIntoOrders_sql(int PID, int DAccnumber,int OID,String Title){
        try {
			connection.setAutoCommit(false);
			try {
                insertIntoOrdersQuery.setInt(1, PID);
                insertIntoOrdersQuery.setInt(2, DAccnumber);
                insertIntoOrdersQuery.setInt(3, OID);
                insertIntoOrdersQuery.setString(4, Title);
                insertIntoOrdersQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public static void addDistributorOwe_sql(int DAccnumber,int order_value){
        try {
			connection.setAutoCommit(false);
			try {
                addDistributorOweQuery.setInt(1, order_value);
                addDistributorOweQuery.setInt(2, DAccnumber);
                
                addDistributorOweQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    public static void insertIntoDistributorpayments_sql(int DAccnumber,String date,int amount_paid){
        try {
			connection.setAutoCommit(false);
			try {
                insertIntoDistributorpaymentsQuery.setInt(1, DAccnumber);
                insertIntoDistributorpaymentsQuery.setString(2, date);
                insertIntoDistributorpaymentsQuery.setInt(3, amount_paid);
                insertIntoDistributorpaymentsQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
    public static void subtractDistributorOwe_sql(int DAccnumber,int amount_paid){
        try {
			connection.setAutoCommit(false);
			try {
                subtractDistributorOweQuery.setInt(1, amount_paid);
                subtractDistributorOweQuery.setInt(2, DAccnumber);
                subtractDistributorOweQuery.executeUpdate();
				connection.commit();
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    

	/*********************************************************************************************************************************************** */
     


	/************************************************************************************************************************************************** */
	//report sql
	
	public static void reportCopiesPerDistributorPerMonth_sql(String year,String month) {
	
		try {
String date=year+"-"+month+"%";

            reportCopiesPerDistributorPerMonthQuery.setString(1, date);
           // reportCopiesPerDistributorPerMonthQuery.setString(2, month);
			result = reportCopiesPerDistributorPerMonthQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nReport of copies per distributor per month\n");
			while (result.next()) {
				System.out.print("\t DAccnumber " + result.getInt("DAccnumber") + " | ");
				System.out.print("PID " + result.getInt("PID") + " | ");
				System.out.print("order value " + result.getInt("ordervalue") + " | ");
				System.out.print("No of copies " + result.getInt("noofcopies") + " \n ");
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success");
	}

    public static void reportTotalRevenuePerMonth_sql(String year,String month){
   try {

			String s=year+'-'+month+'%';
            reportTotalRevenuePerMonthQuery.setString(1, s);
           // reportTotalRevenuePerMonthQuery.setString(2, month);
			result = reportTotalRevenuePerMonthQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nTotal revenue for the given month\n");
			while (result.next()) {
				System.out.print("\t Amount Paid " + result.getInt("amountpaid")+'\n' );
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success"); 
   }
    public static void reportTotalExpensesPerMonth_sql(String year,String month){
  try {   
                int result1=0;
                int result2=0;
                String date=year+"-"+month+"%";
                reportStaffExpensesPerMonthQuery.setString(1, date);
                        result = reportStaffExpensesPerMonthQuery.executeQuery();
                        
                        result.beforeFirst();
                        System.out.println("\nTotal revenue for the given month\n");
                        while (result.next()) {
                                result1=result.getInt("amount");
			}
                        reportShippingExpensesPerMonthQuery.setString(1, date);
                        result = reportShippingExpensesPerMonthQuery.executeQuery();
                        
                        result.beforeFirst();
                        System.out.println("\nTotal revenue for the given month\n");
                        while (result.next()) {
                                         result2=result.getInt("amountpaid");

                        
                        }
                        System.out.println("\n total expenses : "+ result1+result2+"\n");
                                
                
} catch (Throwable err) {
                        err.printStackTrace();
                }
                System.out.println("Success");

}
    public static void reportNumberOfDistributors_sql(){
        try {
			result = reportNumberOfDistributorsQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nTotal number of Distributors\n");
			while (result.next()) {
				System.out.print("\t Number of Distributors " + result.getInt("noofdistributors")+"\n" );
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success");
    }
    public static void reportRevenuePerCity_sql(){
        try {
			result = reportRevenuePerCityQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nRevenue per city\n");
			while (result.next()) {
                System.out.print("\t City " + result.getString("city")+"|" );
                System.out.print("\t Amount Paid " + result.getInt("amountpaid")+"\n" );
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success");
    }
    public static void reportRevenuePerDistributor_sql(){
        try {
			result = reportRevenuePerDistributorQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nRevenue per Distributor\n");
			while (result.next()) {
                System.out.print("\t Distributor Account Number  " + result.getInt("DAccnumber")+"|" );
                System.out.print("\t Amount Paid " + result.getInt("amountpaid") +"\n");
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success");
    }
    public static void reportRevenuePerLocation_sql(){
        try {
			result = reportRevenuePerLocationQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nRevenue per Location\n");
			while (result.next()) {
                System.out.print("\t City " + result.getString("city")+"|" );
                System.out.print("\t Streat Address  " + result.getString("staddress")+"|" );
                System.out.print("\t Amount Paid " + result.getInt("amountpaid")+"\n" );
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success");
    }
    
    public static void reportTotalPaymentsPerPeriodPerType_sql(String year,String month){
        try {
		String date=year+"-"+month+"%";
            reportTotalPaymentsPerPeriodPerTypeQuery.setString(1, date);
           // reportTotalPaymentsPerPeriodPerTypeQuery.setString(2, month);
			result = reportTotalPaymentsPerPeriodPerTypeQuery.executeQuery();

			result.beforeFirst();
			System.out.println("\nReport of copies per distributor per month\n");
			while (result.next()) {
				System.out.print("\t Job Role " + result.getString("jobrole") + " | ");
				System.out.print("Amount " + result.getInt("amount") + " | ");
			
			}

		} catch (Throwable err) {
			err.printStackTrace();
		}
		System.out.println("Success");
	}


/****************************************************************************************************************************** */
	

	/************************************************************************************************************************** */



	//calling functions:
	/************************************************************************************************************************ */
	//report
      
	public static void reportCopiesPerDistributorPerMonth(){
        try {
            System.out.print("\n Enter the following details to generate a monthly report \n");
            System.out.print("\n Enter the year\n");
            String year = s.nextLine();
            System.out.print("\n Enter the month\n");
            String month = s.nextLine();      
            reportCopiesPerDistributorPerMonth_sql(year,month);  
	}                                                                                                                                                                                                                                                                                                                                                                                                                                    catch (Throwable err) {
        	err.printStackTrace();
    	}
    }

    public static void reportTotalRevenuePerMonth(){
        try {
            System.out.print("\n Enter the following details to generate a report of total revenue per month \n");
            System.out.print("\n Enter the year\n");
            String year = s.nextLine();
            System.out.print("\n Enter the month\n");
            String month = s.nextLine();      
            reportTotalRevenuePerMonth_sql(year,month);     
	}                                   
	catch (Throwable err) {
     	   err.printStackTrace();
    	}
    }

    public static void reportTotalExpensesPerMonth(){
        try {
            System.out.print("\n Enter the following details to generate a report of total expenses per month \n");
            System.out.print("\n Enter the year\n");
            String year = s.nextLine();
            System.out.print("\n Enter the month\n");
            String month = s.nextLine();      
            reportTotalExpensesPerMonth_sql(year,month);   
	}                                                                                                                                                                                                                                                                                                                                                                                                                                     catch (Throwable err) {
        	err.printStackTrace();
    	}
    }
    public static void reportNumberOfDistributors(){
        try {    
            reportNumberOfDistributors_sql();    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            	catch (Throwable err) {
        	err.printStackTrace();
    	}
    }
    public static void reportRevenuePerCity(){
        try {    
            reportRevenuePerCity_sql();   }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  	catch (Throwable err) {
        	err.printStackTrace();
    	}
    }
    public static void reportRevenuePerDistributor(){
        try {    
            reportRevenuePerDistributor_sql();     }                                                                                                                                                                                                                                                                                                                                                                                          catch (Throwable err) {
	     	   err.printStackTrace();
    	}
    }
    public static void reportRevenuePerLocation(){
        try {    
            reportRevenuePerLocation_sql();     }                                                                                                                                                                                                                                                                                                                                                                                         	catch (Throwable err) {
        	err.printStackTrace();
    	}
    }
    public static void reportTotalPaymentsPerPeriodPerType(){
        try {    
            System.out.print("\n Enter the following details to generate a report of total paymenst per period per work type \n");
            System.out.print("\n Enter the year\n");
            String year = s.nextLine();
            System.out.print("\n Enter the month\n");
            String month = s.nextLine();      
            reportTotalPaymentsPerPeriodPerType_sql(year, month);         
	}                                                          
	catch (Throwable err) {
        	err.printStackTrace();
    	}
   }

	/************************************************************************************************************************ */


    public static void  addNewBookEdition(){
        try {
                int PID = newPublicationsId();
                System.out.print("\n Enter the details of the Book \n");
                System.out.print("\n Title\n");
                String title = s.nextLine();
                System.out.print("\n Amount \n");
                int amount = s.nextInt();
                s.nextLine();
                System.out.print("\n Type\n");
                String type = s.nextLine();
                System.out.print("\n Topic\n");
                String topic = s.nextLine();
                System.out.print("\n ISBN\n");
                int isbn = s.nextInt();
		s.nextLine();
                System.out.print("\n Edition\n");
                int edition = s.nextInt();
 		s.nextLine();              
 System.out.print("\n Date of the publication\n");
                String dop = s.nextLine();
                addNewPublication_sql(PID, amount,title, topic, type);
                addNewBookEdition_sql(PID, isbn, edition, dop);
        } catch (Throwable err) {
            err.printStackTrace();
        }
        
    }
	
    
    public static void updateBookEdition(){
       try {
                listBookEdition();
                System.out.print("\n Enter the details of the Book you want to edit\n");
                System.out.print("\n PID\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.println("Select the attribute you want to update" );
                System.out.println("1 - " );
                System.out.println("\t-- ISBN");
                System.out.println("2 - " );
                System.out.println("\t-- Edition");
                System.out.println("3 - " );
                System.out.println("\t-- DOP");
                String attribute =s.nextLine();
                System.out.println("Enter the new value");
                String newValue = s.nextLine();
                updateBookEdition_sql(PID, attribute, newValue); 
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }

    public static void deleteBookEdition(){
         try {
            // Get staff id
            listBookEdition();
            System.out.println("\nEnter the Publication of the ID of the Book you want to delete:\n");
            int PID = s.nextInt();
            s.nextLine();

            // Call method that interacts with the Database
            deleteBookEdition_sql(PID);
            System.out.println("The Book is deleted successfully!");
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }

    public static void updatePeriodicIssue(){
        try {
                listPeriodicIssues();
                System.out.print("\n Enter the details of the Peridic Issue you want to edit\n");
                System.out.print("\n PID\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.println("Select the attribute you want to update" );
                System.out.println("1 - " );
                System.out.println("\t-- DOI");
                System.out.println("2 - " );
                System.out.println("\t-- Periodicity");
                String attribute =s.nextLine();
                System.out.println("Enter the new value");
                String newValue = s.nextLine();
                updatePeriodicIssue_sql(PID, attribute, newValue); 
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }
	public static void deletePeriodicIssue(){
       try {
            // Get staff id
            listPeriodicIssues();
            System.out.println("\nEnter the Publication ID of the Periodic issue you want to delete:\n");
            int PID = s.nextInt();
            s.nextLine();
            // Call method that interacts with the Database
            deletePeriodicIssue_sql(PID);
            System.out.println("The Periodic issue is deleted successfully!");
        } catch (Throwable err) {
            err.printStackTrace();
        }

    }
    
    public static void  addArticle(){
        try {
                
                listPeriodicIssues();
                System.out.print("\n Enter the details of the Article\n");
                System.out.print("\n Publication ID of the Book\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.print("\n Title \n");
                String title = s.nextLine();
                System.out.print("\n Date of Creation\n");
                String DOC = s.nextLine();
                System.out.print("\n Article Text\n");
                String text = s.nextLine();
                System.out.print("\n Topic of the Article\n");
                String topic = s.nextLine();
                addArticle_sql(PID,title,DOC,text,topic);
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }
	
    public static void addChapter(){
        try {
                listBookEdition();
                System.out.print("\nEnter the details \n");
                System.out.print("\n Enter the Publication ID of the Book\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.print("\n Enter the Title of the Chapter\n");;
                String title = s.nextLine();
                System.out.print("\n Text\n");
                String text = s.nextLine();
                System.out.print("\nDate of Creation\n");
                String DOC = s.nextLine();
                addChapter_sql(PID,title,text,DOC);
        } catch (Throwable err) {
            err.printStackTrace();
        }   
    }

    public static void updateChapter(){
        try {
                listBookEdition();
                System.out.print("\n Enter the details of the Book you want to edit\n");
                System.out.print("\n PID\n");
                final int PID = s.nextInt();
		s.nextLine();
                listChapters(PID);
                System.out.print("\n Title of the chapter you want to update\n");
                final String title = s.nextLine();
                System.out.println("Select the attribute you want to update" );
                System.out.println("1 - " );
                System.out.println("\t-- Title");
                System.out.println("2 - " );
                System.out.println("\t-- DOC");
                System.out.println("3 - " );
                System.out.println("\t-- Text");
                System.out.println("4 - " );
                System.out.println("\t-- Author");
                final String attribute =s.nextLine();
                if(attribute=="4") listAuthors();
                System.out.println("Enter the new value(If author enter their SID)");
                final String newValue = s.nextLine();
                updateChapter_sql(PID,title,attribute, newValue); 
        } catch (final Throwable err) {
            err.printStackTrace();
        }
    }

    public static void addArticleText(){
        try {
                listArticles();
                System.out.print("\nEnter the details of the Article\n");
                System.out.print("\nPublication ID of the Periodic Issue\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.print("\n Title \n");
                String title = s.nextLine();
                System.out.print("\n Text\n");
                String text = s.nextLine();
                updateArticleText_sql(PID,title,text);
        } catch (Throwable err) {
            err.printStackTrace();
        }   
        
    }
    public static void updateArticleText(){
        try {
                listArticles();
                System.out.print("\nEnter the details of the Article\n");
                System.out.print("\nPublication ID of the Periodic Issue\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.print("\n Title \n");
                String title = s.nextLine();
                System.out.print("\n Text\n");
                String text = s.nextLine();
                updateArticleText_sql(PID,title,text);
        } catch (Throwable err) {
            err.printStackTrace();
        }   
    }
    

	public static void findBooks(){
	try{
		   System.out.println("Select the attribute you want to find books with : " );
                    System.out.println("1 - " );
                    System.out.println("\t-- Topic");
                    System.out.println("2 - " );
                    System.out.println("\t-- Date");
                    System.out.println("3 - ");
                    System.out.println("\t-- Author's name");
                    String attribute =s.nextLine();
		    System.out.println("enter the value of attribute you want to search  in books : " );
		     String attr_val= s.nextLine();
		    findBooks_sql(attribute, attr_val);
	}
	catch(Throwable err){
	err.printStackTrace();
	}
}


	public static void findArticles(){
	try{
		    System.out.println("Select the attribute you want to find articles with : " );
                    System.out.println("1 - " );
                    System.out.println("\t-- Topic");
                    System.out.println("2 - " );
                    System.out.println("\t-- Date");
                    System.out.println("3 - ");
                    System.out.println("\t-- Jounlist's name");
                    String attribute =s.nextLine();
 		    System.out.println("enter the value of attribute you want to search in Articles : " );
                     String attr_val= s.nextLine();
		    findArticle_sql(attribute, attr_val);
	}
	catch(Throwable err){
	err.printStackTrace();
	}
}


    public static void addStaffPayment() {
        try {
            listStaff();
            System.out.print("\n Enter the details\n");
            System.out.print("\n Staff ID of the Staff member\n");
            final int SID = s.nextInt();
		s.nextLine();
            System.out.print("\n Date of the payment \n");
            final String DOP = s.nextLine();
            System.out.print("\n Amount paid\n");
            final int amount = s.nextInt();
	s.nextLine();
            System.out.print("\n Date of collection\n");
            final String DOC = s.nextLine();
            addStaffPayment_sql(SID, DOP, amount, DOC);
        } catch (final Throwable err) {
            err.printStackTrace();
        }
    }

    public static void updateStaffPayment() {
        try {
            listStaff();
            System.out.print("\n Enter the details\n");
            System.out.print("\n Staff ID of the Staff member whose date of collection you want to update\n");
            final int SID = s.nextInt();
	s.nextLine();
            System.out.print("\n Enter Date of the payment \n");
            final String DOP = s.nextLine();
            System.out.print("\n Date of collection\n");
            final String DOC = s.nextLine();
            updateStaffPayment_sql(SID,DOP,DOC);
        } catch (final Throwable err) {
            err.printStackTrace();
        }
    }
    


public static void newPublicationEntry(){
		try {
			System.out.print("\n Enter New Publication details \n ");
			int PID = newPublicationsId();
			System.out.print("\n Enter Title of the Publication \n ");  
            String Title = s.nextLine();
            System.out.print("\n Enter Price \n ");
            int Price = s.nextInt();
			s.nextLine();
            System.out.print("\n Enter Topic  \n ");
            String Topic = s.nextLine();
            String Type;
            Boolean b = false;
            while(b==false){
            System.out.print("\n Select Type\n1.Book\n2.Magazine\n3.Journal\n ");
            int Typeno = s.nextInt();
			s.nextLine();
            switch(Typeno){
            case 1:{
                Type ="Book";
                        System.out.print("\n Enter ISBN of Book\n ");
            int ISBN = s.nextInt();
			s.nextLine();
            System.out.print("\n Enter Book edition\n ");
            int edition = s.nextInt();
			s.nextLine();
            System.out.print("\n Enter date of publication\n ");
            String dop = s.nextLine();
            addNewPublication_sql(PID,Price,Title,Topic,Type);
            addNewBookEdition_sql(PID,ISBN,edition,dop);
                b=true;
                break;}
            case 2:{
                Type="Magazine";
            System.out.print("\nEnter Date of Issue \n ");
            String DOI = s.nextLine();
            System.out.print("\n Enter periodicity\n ");
            String periodicity = s.nextLine();
            addNewPublication_sql(PID,Price,Title,Topic,Type);
            addPeriodicIssue_sql(PID,DOI,periodicity);

            b=true;
                break;}
            case 3:{
                Type="Journal";
                System.out.print("\nEnter Date of Issue \n ");
            String DOI = s.nextLine();
            System.out.print("\n Enter periodicity\n ");
            String periodicity = s.nextLine();
            addNewPublication_sql(PID,Price,Title,Topic,Type);
            addPeriodicIssue_sql(PID,DOI,periodicity);

                b=true;
                break;}
            default:
                System.out.println("Wrong input \n give correct input");
            }
		}
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}



public static void updatePublicationInfo(){
		try { listPublications();
			System.out.print("\n Update details of a PublicationInfo \n");
			System.out.print("\n Enter PID of the publication you want to update\n");
		int PID = s.nextInt();
		s.nextLine();
	        System.out.println("Select the attribute you want to update" );
                    System.out.println("1 - " );
                    System.out.println("\t-- Price");
                    System.out.println("2 - " );
                    System.out.println("\t-- Title");
                    System.out.println("3 - ");
                    System.out.println("\t-- Topic");
                    System.out.println("4 - " );
        System.out.println("\t-- Type");
		String attr = s.nextLine();
		System.out.print("\n Enter the value you want to update it to\n");
		String value = s.nextLine();
		updatePublicationInfo_sql(PID,attr,value);
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}

public static void assignEditor(){
		try {
			
			System.out.print("\n assignEditor  \n> ");
			 listPublications();
			System.out.print("\n Enter the PID of the publication you want to Assign an editor\n");
			
		int PID = s.nextInt();
		s.nextLine();
		listEditors();
		System.out.print("\n Enter the SID of the Editor you want to Assign to a publication\n");
		int SID = s.nextInt();
		s.nextLine();
assignEditor_sql(PID,SID);


				
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}





public static void editorPublicationsView(){
		try {
			
			System.out.print("\n editors-Publications \n> ");
			listEditors();
			System.out.print("\n Enter the SID of the Editor whose publications you want to see\n");
		int SID = s.nextInt();
		s.nextLine();
		editorPublicationsView_sql(SID);
	
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}


	public static void addingBookChapters(){
		try {
			listBooks();				
System.out.print("\n Enter the PID of the Book whose chapter you want to edit\n");
	int PID = s.nextInt();
	s.nextLine();
	System.out.print("\n Enter the Title of the Chapter you want to add \n");
	String Title=s.nextLine(); 

	System.out.print("\n Enter the  Text of the Chapter you want to add \n");
	String Text=s.nextLine();

	System.out.print("\n Enter the  Date of Creation of the Chapter \n");
	String DateofCreation=s.nextLine(); 

	addBookChapters_sql(PID,Title,Text,DateofCreation);



		} catch (Throwable err) {
			err.printStackTrace();
		}
	}






public static void deletingBookChapters(){
		try {
			
			System.out.print("\n deletingBookChapters()  \n> ");
			listBooks();
        System.out.print("\n Enter the PID of the Book whose chapter you want to delete\n");
		int PID = s.nextInt();
		s.nextLine();
        System.out.println("The Chapters of the Book are as follows:");
			listChapters(PID);
		System.out.print("\n Enter the Title of the Chapter you want to delete");
		String title=s.nextLine(); 
		deletingBookChapters_sql(PID,title);


		} catch (Throwable err) {
			err.printStackTrace();
		}
	}




	public static void addingArticleInPeriodicIssue(){
		try {
		listPeriodicIssues();			
System.out.print("\n Enter the PID of the Periodic issue whose article you want to edit\n");
	int PID = s.nextInt();
	s.nextLine();
	System.out.print("\n Enter the Title of the Article you want to add \n");
	String Title=s.nextLine(); 

	System.out.print("\n Enter the  Text of the Article you want to add \n");
	String Text=s.nextLine();

	System.out.print("\n Enter the  Date of Creation of the Article \n");
	String DateofCreation=s.nextLine();
	
	System.out.println("\nEnter Topic:\n");
	String Topic=s.nextLine();
	 

	addArticleInPeriodicIssue_sql(PID,Title,Text,DateofCreation,Topic);

		} catch (Throwable err) {
			err.printStackTrace();
		}
	}



public static void deletingArticleInPeriodicIssue(){
		try {
			
			System.out.print("\n deletingArticleInPeriodicIssue()  \n> ");
			listPeriodicIssues();
System.out.print("\n Enter the PID of the PeriodIssue whose Article you want to delete\n");
		int PID = s.nextInt();
		s.nextLine();
		System.out.println("The Articles of the PeriodicIssue are as follows:");
			listArticlesOfPublication(PID);
		System.out.print("\n Enter the Title of the Article you want to delete");
		String title=s.nextLine(); 
		deletingArticleInPeriodicIssue_sql(PID,title);

		} catch (Throwable err) {
			err.printStackTrace();
		}
	}


	

public static void addDistributor(){
        try {
                int daccnumber = getnewDistID();
                System.out.print("\n Enter the details of the Distributor \n");
                System.out.print("\n Name of the Contact Person\n");
                String contactperson = s.nextLine();
                System.out.print("\n Phone Number \n");
                String phone = s.nextLine();
                System.out.print("\n City\n");
                String city = s.nextLine();
                System.out.print("\n Address\n");
                String address = s.nextLine();
                System.out.print("\n Distributor Type\n");
                String disttype = s.nextLine();
                System.out.print("\n Name of the Distributor\n");
                String distname = s.nextLine();
                System.out.print("\n The previous due amount\n");
                String owe = s.nextLine();
                addDistributor_sql(daccnumber,contactperson,phone,city,address,disttype,distname,owe);
        } catch (Throwable err) {
            err.printStackTrace();
        }
    }

public static void distributorOrder(){
		try {
            System.out.print("\n Input Order  \n> ");
            listDistributors();
            System.out.print("\n Enter the DAccnumber of the distributor who is making the order\n");
		int DAccnumber = s.nextInt();
		s.nextLine();
			listPublications();
        System.out.print("\n Enter the PID of the Publication the distributor want to order\n");
        int PID=s.nextInt();
		s.nextLine();
        String title=getPIDTitle_sql(PID);
        int OID=newOrderId();   
        System.out.print("\n Enter the Number of copies you want to order\n");
        int noc=s.nextInt();
		s.nextLine();
        System.out.print("\n Enter the Order daten");
        String order_date=s.nextLine();
        System.out.print("\n Enter the Order due daten");
        String order_due_date=s.nextLine();
        int price_PID=getPricePID_sql(PID);
        int order_value=price_PID*noc;
        int shipping_cost=order_value/10;
        insertIntoOrder_Tables_sql(OID,noc,order_date,order_due_date,order_value,shipping_cost);
        insertIntoOrders_sql(PID,DAccnumber,OID,title);
        addDistributorOwe_sql(DAccnumber,order_value);
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}

 public static void  addPeriodicIssue(){
        try {
                int PID = newPublicationsId();
                System.out.print("\n Enter the details of the Periodicity issue \n");
                System.out.print("\n Title\n");
                String title = s.nextLine();
                System.out.print("\n Amount \n");
                int amount = s.nextInt();
                s.nextLine();
                System.out.print("\n Type\n");
                String type = s.nextLine();
                System.out.print("\n Topic\n");
                String topic = s.nextLine();
                System.out.print("\n Date of Isuue\n");
                String doi = s.nextLine();
                System.out.print("\n Periodiicity\n");
                String periodicity = s.nextLine();
                addNewPublication_sql(PID, amount,title, topic, type);
                addPeriodicIssue_sql(PID, doi, periodicity);
        } catch (Throwable err) {
            err.printStackTrace();
        }
	}
        
    public static void updateArticle(){
        try {
                listPeriodicIssues();
                System.out.print("\n Enter the details of the Article you want to edit\n");
                System.out.print("\n PID\n");
                int PID = s.nextInt();
                s.nextLine();
                System.out.println(" Enter the Title of the Article you want to update" );
                String title = s.nextLine();
                System.out.println("Select the attribute you want to update" );
                System.out.println("1 - " );
                System.out.println("\t-- DOC");
                System.out.println("2 - " );
                System.out.println("\t-- Text");
                System.out.println("3 - " );
                System.out.println("\t-- Topic");
                String attribute =s.nextLine();
                System.out.println("Enter the new value");
                String newValue = s.nextLine();
                updateArticle_sql(PID,title, attribute, newValue); 
        } catch (Throwable err) {
            err.printStackTrace();
        }
       
    }

public static void billDistributor(){
		try {
			System.out.println("Billed while inputing the order itself");
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}

public static void updateDistributorPayment(){
		try {
            System.out.print("\n Distributor Payment\n");
            listDistributors();
            System.out.print("\n Enter the DAccnumber of the distributor who is making the payemnt\n");
            int DAccnumber= s.nextInt();
			s.nextLine();
            System.out.print("\n Enter the amount paid\n");
            int amount_paid=s.nextInt();
			s.nextLine();
            System.out.print("\n Enter the date paid\n");
            String date=s.nextLine();
            insertIntoDistributorpayments_sql(DAccnumber,date,amount_paid);
            subtractDistributorOwe_sql(DAccnumber,amount_paid);
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}

	public static void deleteAllTables(Statement statement){
		String[] tableNames={"StaffPayments","Distributorpayments","Orders","Order_tables","Chapters","Articlewrites","Articles","Bookwrites","Books","Periodicissues","Admin","Edits","Authors","Editors","Journalists","Distributors","Publications","Staff"};
		try {
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
			for (String name : tableNames) {
				statement.executeUpdate("DROP TABLE " + name + ";");
			}
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
		} catch (Throwable err) {
			err.printStackTrace();
		}
	}    	



	public static void printMainMenu(){
        System.out.println("1.Editing and publishing\n2.Production\n3.Distribution\n4.Reports\n5.quit");
    }

	public static void printEditMenu(){
      	boolean b=false;

            while(b==false){   System.out.println("1 - " );
        System.out.println("\t-- Enter information of a new publication record");
        System.out.println("2 - " );
        System.out.println("\t-- Update existing publication information");
        System.out.println("3 - ");
        System.out.println("\t-- Assign an Editor to a Publication");
        System.out.println("4 - " );
        System.out.println("\t-- View of all the publications assigned to an Editor");
        System.out.println("5 - " );
        System.out.println("\t-- Add chapters of a book");
        System.out.println("6 - " );
        System.out.println("\t-- Delete chapters of a book");
        System.out.println("7 - ");
        System.out.println("\t-- - Add Artciles in the periodic issue");
        System.out.println("8 - " );
        System.out.println("\t-- - Delete articles in the periodic issue");
        System.out.println("9 - " );
        System.out.println("\t-- go back to main menu");
        System.out.println("10 - " );
        System.out.println("\t-- exit the program");
            
                String temp=s.nextLine();
                switch(temp){   
                    case "1":
					newPublicationEntry();										
					break;
				case "2":
					updatePublicationInfo();
                    break;
				case "3":
					assignEditor();
					break;
                case "4":
					editorPublicationsView();
					break;					
                case "5":
					addingBookChapters();
					break;
				case "6":
					deletingBookChapters();
					break;
				case "7":
					addingArticleInPeriodicIssue();
				    break;
				case "8":
					deletingArticleInPeriodicIssue();
					break;
				
                case "9":
					return;
				case "10":
					System.exit(0);
					break;
				default:
					System.out.println("\nCommand not recognized");
					break;
                        
                        
                }
                

            }
    }
    public static void printProdMenu(){
      boolean b=false;

            while(b==false){   System.out.println("1 - " );
			System.out.println("\t-- Enter a new book edition");
			System.out.println("2 - " );
			System.out.println("\t-- Update book edition");
			System.out.println("3 - " );
			System.out.println("\t-- , delete book edition ");
			System.out.println("4 - " );
			System.out.println("\t-- Enter new periodic issue");
			System.out.println("5 - " );
			System.out.println("\t--Update periodic issue");
			System.out.println("6 - " );
			System.out.println("\t--delete periodic issue");
			System.out.println("7 - " );
			System.out.println("\t-- Enter an article ");
			System.out.println("8 - " );
			System.out.println("\t-- - update an article ");
			System.out.println("9 - " );
			System.out.println("\t-- -Enter a chapter");
			System.out.println("10 - " );
			System.out.println("\t-- -update a chapter");
			System.out.println("11 - " );
			System.out.println("\t-- - Enter text of article");
			System.out.println("12 - " );
			System.out.println("\t-- - update text of article");
			System.out.println("13 - " );
			System.out.println("\t-- - Find books");
			System.out.println("14 - " );
			System.out.println("\t-- - Find articles");
			System.out.println("15 - " );
			System.out.println("\t-- - Enter payment for Staff");
			System.out.println("16 - " );
			System.out.println("\t-- - Update Date of collection of payment for Staff");
			System.out.println("17 - " );
			System.out.println("\t-- go back to main menu");
			System.out.println("18 - " );
            System.out.println("\t-- exit the program");
            
                String temp=s.nextLine();
                switch(temp){   
                    case "1":
                        addNewBookEdition();
                        break;
                    case "2":
                        updateBookEdition();
                        break;
                    case "3":
                        deleteBookEdition();
                        break;
                    case "4":
                        addPeriodicIssue();
                        break;
                    case "5":
                        updatePeriodicIssue();
                        break;
                    case "6":
                        deletePeriodicIssue();
                        break;
                    case "7":
                        addArticle();
                        break;
                    case "8":
                        updateArticle();
                        break;
                    case "9":
                        addChapter();
                        break;
                    case "10":
                        updateChapter();
                        break;
                    case "11":
                        addArticleText();
                        break;
                    case "12":
                        updateArticleText();
                        break;
                    case "13":
                        findBooks();
                        break;
                    case "14":
                        findArticles();
                        break;
                    case "15":
                        addStaffPayment();
                        break;
                    case "16":
                        updateStaffPayment();
                        break;
                    case "17":
                         System.out.println("\t-- go back to main menu");
                        return;
                    case "18":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nCommand not recognized");
                        break;                      
                    }                

            }
    }
    public static void printDistMenu(){
        boolean b=false;

            while(b==false){ System.out.println("1 - " );
			System.out.println("\t-- Enter a new Distributor");
			System.out.println("2 - " );
			System.out.println("\t-- Update Distributor");
   			System.out.println("3 - " );
			System.out.println("\t-- Delete a Distributor");
			System.out.println("4 - " );
			System.out.println("\t-- Input orders from distributors, for a book edition or an issue of a publication per distributor, for a certain date");
			System.out.println("5 - " );
			System.out.println("\t-- Bill a distributor for an order");
			System.out.println("6 - " );
			System.out.println("\t-- Update the amount Distribuor owes when payment is made");
			System.out.println("7 - " );
			System.out.println("\t-- go back to main menu");
			System.out.println("8 - " );
			System.out.println("\t-- exit the program");

                String temp=s.nextLine();
                switch(temp){
                    case "1":
						addDistributor();
						break;
					case "2":
						updateDistributor();
						break;
					case "3":
						deleteDistributor();
						break;
					case "4":
						distributorOrder();
						break;
					case "5":
						billDistributor();
						break;
					case "6":
						updateDistributorPayment();
						break;
					case "7":
						return;
					case "8":
						System.exit(0);
						break;
					default:
						System.out.println("\nCommand not recognized");

						break;
                }
                

            }
    }
    public static void printReportMenu(){
        		 boolean b=false;

            while(b==false){
			System.out.println("1 - " );
			System.out.println("\t-- Number and total price of copies per distributor per month");
			System.out.println("2 - " );
			System.out.println("\t-- Total revenue of publishing house per month");
			System.out.println("3 - " );
			System.out.println("\t-- Total expenses of publishing house per month");
			System.out.println("4 - " );
			System.out.println("\t-- Calculate number of distributors");
		    System.out.println("5 - " );
			System.out.println("\t 5-- Calculate total revenue per city");
		    System.out.println("6 - " );
			System.out.println("\t-- Calculate total revenue per Distributor");
            System.out.println("7 - " );
            System.out.println("\t-- Calculate total revenue per Location");
			System.out.println("8 - " );
			System.out.println("\t-- Calculate total payment made to authors and editors per time period,per work type");
			System.out.println("9 - " );
            System.out.println("\t-- go back to main menu");
            System.out.println("10- " );
            System.out.println("\t-- exit");
                String temp=s.nextLine();
                switch(temp){
                    case "1":
                		reportCopiesPerDistributorPerMonth();	
               			break;
            		case "2":
               			reportTotalRevenuePerMonth();
					    break;
            		case "3":
                			reportTotalExpensesPerMonth();
                			
					break;
        		    	case "4":
                			reportNumberOfDistributors();
                			
	    				break;
            			case "5":
                			reportRevenuePerCity();
					
                			break;
            			case "6":
                			reportRevenuePerDistributor();
					
                			break;
            			case "7":
                			reportRevenuePerLocation();
                			break;
            			case "8":
                			reportTotalPaymentsPerPeriodPerType();
                			break;
            			case "9":
                            return;
            			case "10":
                            System.exit(0);
                			
            			default:
                			System.out.println("\nCommand not recognized");
                			break;
                }


            }
            
    }


	public static void generateTables(Connection connection,Statement statement) {
		try {
			connection.setAutoCommit(false);
			try {
				
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS  `Staff` ("
						+"`SID` INT,"
						+"`Name` VARCHAR(128) NOT NULL,"
						+"`JobRole` VARCHAR(16) NOT NULL,"
						+"`Age` INTEGER NOT NULL,"
						+"`Gender` VARCHAR(1) NOT NULL,"
						//+"CHECK Gender IN ('M','F','U'),"
						+ "`Phone` VARCHAR(255) NOT NULL, "
                        			+ "`Email` VARCHAR(255) NOT NULL, "
                        			+ "`Address` VARCHAR(255) NOT NULL, "
						+"PRIMARY KEY(`SID`)"
						+");");			
				
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Publications` (" 
						+ "`PID` INT, "
						+ "`AMOUNT` DECIMAL(9,2) NOT NULL, " 
						+ "`TITLE` VARCHAR(30) NOT NULL, "
						+ "`TOPIC` VARCHAR(30) NOT NULL,"
						+ "`TYPE` VARCHAR(30) NOT NULL,"
                        			+ "PRIMARY KEY (`PID`));");	

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Distributors` ("
						+ "`DAccnumber` INT, "
                       				+ "`Contactperson` VARCHAR(255) NOT NULL, "
                        			+ "`Phone` VARCHAR(255) NOT NULL, "
                        			+ "`City` VARCHAR(255) NOT NULL, "
                        			+ "`Staddress` VARCHAR(255) NOT NULL, "
                        			+ "`type` VARCHAR(255) NOT NULL, "
                        			+ "`distributorname` VARCHAR(255) NOT NULL, "
                        			+ "`owe` INT NOT NULL, "
                        			+ "PRIMARY KEY (`DAccnumber`));");

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Order_tables` ("
						+ "`Orderno` INT, "
                        			+ "`noofcopies` INT NOT NULL, "
                        			+ "`orderdate` date NOT NULL, " 
                        			+ "`orderduedate` date NOT NULL, " 
                        			+ "`ordervalue` INT NOT NULL, "
                        			+ "`shippingcost` INT NOT NULL, "
                        			+ "PRIMARY KEY (`Orderno`));");

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS  `Articles` ("
						+"`PID` INT,"
						+"`title` VARCHAR(30) NOT NULL,"
						 +"`text` VARCHAR(1000) NOT NULL,"
						+"`Dateofcreation` date NOT NULL,"
						+"`Topic` VARCHAR(30) NOT NULL,"
						+"PRIMARY KEY(`PID`, `title`),"
						+"FOREIGN KEY(`PID`) REFERENCES `Publications`(`PID`) ON UPDATE CASCADE"
						+" ON DELETE CASCADE"
						+");");
	
				
				statement.executeUpdate(" CREATE TABLE IF NOT EXISTS  `Editors` ("
  						+"`SID` int(11),"
						+"`Pay` int(11) NOT NULL,"
						+"`Type` varchar(30) NOT NULL,"
 						+"PRIMARY KEY (`SID`),"
  						+"FOREIGN KEY (`SID`) REFERENCES `Staff` (`SID`) ON UPDATE CASCADE ON DELETE CASCADE"
						+");");

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Books` (" 
						+ "`PID` INT, "
						+ "`ISBN` INT NOT NULL, " 
						+ "`Edition` INT NOT NULL, "
						+ "`DateofPublication` DATE NOT NULL, "
                        			+ "PRIMARY KEY (`PID`), "
                        			+ "UNIQUE(`ISBN`), "
                        			+ "FOREIGN KEY (`PID`) REFERENCES `Publications`(`PID`) ON DELETE CASCADE ON UPDATE CASCADE);");

				statement.executeUpdate(" CREATE TABLE IF NOT EXISTS `Authors` ("
						+" `SID` int(11) NOT NULL,"
						+"`Pay` int(11) NOT NULL,"
						+"`Type` varchar(30) NOT NULL,"
						+"PRIMARY KEY (`SID`),"
						+"FOREIGN KEY (`SID`) REFERENCES `Staff` (`SID`) ON UPDATE CASCADE ON DELETE CASCADE"
						+");");

			
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Periodicissues` (" 
						+ "`PID` INT, "
						+ "`Dateofissue` DATE NOT NULL, " 
						+ "`Periodicity` VARCHAR(30) NOT NULL, "
                       				+ "PRIMARY KEY (`PID`), "
                        			+ "FOREIGN KEY (`PID`) REFERENCES `Publications`(`PID`) ON DELETE CASCADE ON UPDATE CASCADE);");

                		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Chapters` (" 
						+ "`PID` INT, "
						+ "`Title` VARCHAR(30) NOT NULL, " 
                        			+ "`Text` VARCHAR(10000) NOT NULL, "
                        			+ "`Dateofcreation` DATE NOT NULL, "
                        			+ "PRIMARY KEY (`PID`,`Title`), "
                        			+ "FOREIGN KEY (`PID`) REFERENCES `Books`(`PID`) ON DELETE CASCADE ON UPDATE CASCADE);");
                
                		statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Distributorpayments` (" 
						+ "`DAccnumber` INT, "
						+ "`Paymentdate` DATE NOT NULL, " 
                        			+ "`Amountpaid` INT NOT NULL, "
                        			+ "PRIMARY KEY (`DAccnumber`,`Paymentdate`), "
                        			+ "FOREIGN KEY (`DAccnumber`) REFERENCES `Distributors`(`DAccnumber`) ON DELETE CASCADE ON UPDATE CASCADE);");
                 		
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Edits` (" 
                        			+ "`PID` INT, "
                        			+ "`SID` INT, "
                        			+ "PRIMARY KEY (`PID`,`SID`), "
						+ "CHECK(SID='/' OR SID IN (SELECT SID from Editors)),"
                        			+ "FOREIGN KEY (`PID`) REFERENCES `Publications`(`PID`) ON DELETE CASCADE ON UPDATE CASCADE);");
				
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Journalists` (" 
						+ "`SID` INT, "
						+ "`Salary` INT NOT NULL, " 
						+ "PRIMARY KEY (`SID`), "
                        			+ "FOREIGN KEY(`SID`) REFERENCES `Staff`(`SID`) ON UPDATE CASCADE ON DELETE CASCADE );");

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `StaffPayments` (" 
						+ "`SID` INT, "
						+ "`Salarydate` date NOT NULL, " 
                        			+ "`amount` INT NOT NULL, "
                        			+ "`Dateofcollection` date NOT NULL, "
						+ "PRIMARY KEY (`SID`,`Salarydate`), "
                        			+ "FOREIGN KEY(`SID`) REFERENCES `Staff`(`SID`) ON UPDATE CASCADE ON DELETE CASCADE );");


				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Orders` (" 
		                                + "`PID` INT,"
						+ "`Daccnumber` INT ," 
						+ "`Orderno` INT,"
						+ "`Title` VARCHAR(128) NOT NULL,"
						+ "PRIMARY KEY (`PID`,`DAccnumber`,`Orderno`),"
						+ "FOREIGN KEY (PID) REFERENCES Publications(PID) ON DELETE CASCADE ON UPDATE CASCADE,"
						+ "FOREIGN KEY (`Daccnumber`) REFERENCES `Distributors` (`DAccnumber`) ON DELETE CASCADE ON UPDATE CASCADE,"
						+ "FOREIGN KEY(Orderno) REFERENCES Order_tables(Orderno) ON UPDATE CASCADE ON DELETE CASCADE);");

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Articlewrites` (" 
		                                + "`PID` INT NOT NULL,"
						+ "`SID` INT NOT NULL ," 
						+ "`Title` VARCHAR(128) NOT NULL,"
						+ "PRIMARY KEY(PID,SID,Title),"
						+ "FOREIGN KEY(PID,Title) REFERENCES Articles(PID,Title) ON UPDATE CASCADE ON DELETE CASCADE,"
						+ "FOREIGN KEY(SID) REFERENCES Journalists(SID) ON UPDATE CASCADE ON DELETE CASCADE);");


				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Bookwrites` ("
						+ "`SID` INT NOT NULL,"
						+ "`PID` INT NOT NULL," 
						+ "PRIMARY KEY(PID,SID),"
						+ "FOREIGN KEY(PID) REFERENCES Books(PID) ON UPDATE CASCADE ON DELETE CASCADE,"
						+ "FOREIGN KEY(SID) REFERENCES Authors(SID) ON UPDATE CASCADE ON DELETE CASCADE);");

				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `Admin` (" 
						+ "`SID` INT NOT NULL," 
						+ "PRIMARY KEY(SID),"
						+ "FOREIGN KEY(SID) REFERENCES Staff(SID) ON UPDATE CASCADE ON DELETE CASCADE);");


				connection.commit();
				System.out.println("Tables created!");
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

public static void insert_demodata(Connection connection,Statement statement) {
    try {
			connection.setAutoCommit(false);
			try {


                statement.executeUpdate("INSERT INTO Staff VALUES (3001,'John','staff editor',36,'M','9391234567','3001@gmail.com','21 ABC St, NC 27');");
                statement.executeUpdate("INSERT INTO Staff VALUES (3002,'Ethen','staff editor',30,'M','9491234567','3002@gmail.com','21 ABC St, NC 27606');");
                statement.executeUpdate("INSERT INTO Staff VALUES (3003,'Cathy','invited author',28,'F','9591234567','3003@gmail.com','3300 AAA St, NC 27606');");

                    statement.executeUpdate("INSERT INTO Publications VALUES (1001,100,'introduction to database','technology','Book')");
                    statement.executeUpdate("INSERT INTO Publications VALUES (1002,60,'Healthy Diet','health','magazine')");
                    statement.executeUpdate("INSERT INTO Publications VALUES (1003,60,'Animal Science','science','Journal')");



                statement.executeUpdate("Insert into Distributors VALUES(2001,'jason',9191234567,'charlotte','2200, A Street, NC ','bookstore','BookSell',215);");
                statement.executeUpdate("Insert into Distributors VALUES(2002,'Alex',9291234567,'Raleigh','2200, B Street, NC','wholesaler','BookDist',0);");
                statement.executeUpdate("Insert into Distributors VALUES(3001,'Ross',7197187171,'New York','12, Central Park Apartments','Library','Hunt Library','880');");

                statement.executeUpdate("Insert into Order_tables VALUES(4001,30,'2020-01-02','2020-01-15',630,30);");
                statement.executeUpdate("Insert into Order_tables VALUES(4002,10,'2020-02-05','2020-02-15',215,15);");
                statement.executeUpdate("Insert into Order_tables VALUES(4003,10,'2020-02-10','2020-02-25',100,15);");

                statement.executeUpdate("INSERT INTO Articles VALUES (1002,'Healthy Diet Intro','ABC','2019-03-02','health')");
                statement.executeUpdate("INSERT INTO Articles VALUES (1003,'Animal Science Intro','AAA','2020-02-02','science')");


                statement.executeUpdate("INSERT INTO Editors VALUES (3001,1000,'Permanent');");
                statement.executeUpdate("INSERT INTO Editors VALUES (3002,1000,'Permanent');");

                statement.executeUpdate("INSERT INTO Books VALUES (1001,12345,2,'2018-10-10')");

                statement.executeUpdate("INSERT INTO Authors VALUES (3003,1200,'Temporary');");

                statement.executeUpdate("INSERT INTO Periodicissues VALUES (1002, '2020-02-24','monthly')");
                statement.executeUpdate("INSERT INTO Periodicissues VALUES (1003, '2020-03-01','monthly')");

//chapter

                statement.executeUpdate("Insert into Distributorpayments VALUES(2001,'2020-01-02',630);");
                statement.executeUpdate("Insert into Distributorpayments VALUES(2002,'2020-02-10',115);");

                statement.executeUpdate("Insert into Edits VALUES(1001,1);");
                statement.executeUpdate("Insert into Edits VALUES(1002,2);");
                statement.executeUpdate("Insert into Edits VALUES(1003,3);");

                statement.executeUpdate("Insert into StaffPayments VALUES(3001,'2020-04-01',1000,'2020-04-01');");
                statement.executeUpdate("Insert into StaffPayments VALUES(3002,'2020-04-01',1000,'2020-04-01');");
                statement.executeUpdate("Insert into StaffPayments VALUES(3003,'2020-04-01',1200,'2020-04-01');");



                statement.executeUpdate("Insert into Orders VALUES(1001,2001,4001,'introduction to database');");
                statement.executeUpdate("Insert into Orders VALUES(1001,2001,4002,'introduction to database');");
                statement.executeUpdate("Insert into Orders VALUES(1003,2002,4003,'Animal Science');");

//articleswrites

//bookwrites



                } catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

}

public static void insert_into_Tables(Connection connection,Statement statement) {
		try {
			connection.setAutoCommit(false);
			try {
				
				
				statement.executeUpdate("INSERT INTO Staff VALUES (1,'Hash','Admin',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (2,'josh','editor',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (3,'Rosh','editor',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (4,'Mike','editor',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (5,'John','editor',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (6,'Ram','editor',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (7,'Jenny','author',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (8,'Mia','author',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (9,'Ria','author',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (10,'Jim','author',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (11,'Tim','author',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (12,'kim','Journalist',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (13,'swathi','Journalist',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (14,'sam','Journalist',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (15,'raj','Journalist',10,'M','9999999999','abc@gmail.com','2312')");
				statement.executeUpdate("INSERT INTO Staff VALUES (16,'harsha','Journalist',10,'M','9999999999','abc@gmail.com','2312')");

				statement.executeUpdate("INSERT INTO Publications VALUES (1, 400.00,'Harry Potter','fiction','book')");
				statement.executeUpdate("INSERT INTO Publications VALUES (2, 200.00,'Twilight','fiction','book')");
				statement.executeUpdate("INSERT INTO Publications VALUES (3, 50.00,'Wings of Fire','fiction','book')");
				statement.executeUpdate("INSERT INTO Publications VALUES (4, 600.00,'Lord of the Rings','fiction','book')");
				statement.executeUpdate("INSERT INTO Publications VALUES (5, 450.00,'Game of Thrones','fiction','book')");
				statement.executeUpdate("INSERT INTO Publications VALUES (6, 20.00,'Top Gear','sport','magazine')");
				statement.executeUpdate("INSERT INTO Publications VALUES (7, 10.00,'Wolf Pack','sport','magazine')");
				statement.executeUpdate("INSERT INTO Publications VALUES (8, 30.00,'Sakhi','sport','journal')");
				statement.executeUpdate("INSERT INTO Publications VALUES (9, 15.00,'Film Dare','sport','magazine')");
				statement.executeUpdate("INSERT INTO Publications VALUES (10, 20.00,'South Scope','sport','magazine')");

				statement.executeUpdate("Insert into Distributors VALUES(1,'Ross',7197187171,'New York','12, Central Park Apartments','Library','Hunt Library','880');");
				statement.executeUpdate("Insert into Distributors VALUES(2,'Monica',7197187172,'New York','21, Central Park Apartments','Library','Hill Library','880');");
				statement.executeUpdate("Insert into Distributors VALUES(3,'Phoebe',7197187173,'New York','13, Central Park Apartments','Store','Hunt Stores','440');");
				statement.executeUpdate("Insert into Distributors VALUES(4,'Rachael',7197187174,'New York','31, Central Park Apartments','Store','Hill Stores','440');");
				statement.executeUpdate("Insert into Distributors VALUES(5,'Chandler',7197187175,'New York','41, Central Park Apartments','Individual','Chandler','880');");
				statement.executeUpdate("Insert into Distributors VALUES(6,'Joey',719718716,'New York','19, Central Apartments Park','Individual','Joey','880');");	
				
				statement.executeUpdate("Insert into Order_tables VALUES(1,2,'2019-03-02','2020-04-30',800,80);");
				statement.executeUpdate("Insert into Order_tables VALUES(2,2,'2019-03-13','2020-05-01',800,80);");
				statement.executeUpdate("Insert into Order_tables VALUES(3,2,'2019-03-09','2020-05-02',400,40);");
				statement.executeUpdate("Insert into Order_tables VALUES(4,2,'2019-03-13','2020-05-03',400,40);");
				statement.executeUpdate("Insert into Order_tables VALUES(5,2,'2019-03-17','2020-05-04',800,40);");
				statement.executeUpdate("Insert into Order_tables VALUES(6,2,'2019-03-13','2020-05-03',800,80);");

				statement.executeUpdate("INSERT INTO Articles VALUES (6,'The Future','into the unknown','2019-03-02','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (6,'New releases','Mustang 3R','2019-03-13','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (7,'Healthy heart','Live LOnger','2019-03-09','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (7,'NCSU VS UNCC','The pack howls again','2019-03-13','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (8,'Live your Life','Best Movie','2019-03-17','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (8,'Healthify','Wash your hands','2019-03-13','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (9,'Mr.Majnu','Legend','2019-03-18','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (9,'Awards','Filmfare','2019-03-13','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (10,'Movie Lovers','Get ur tickets','2019-03-20','technology')");
				statement.executeUpdate("INSERT INTO Articles VALUES (10,'Upcoming Movies','komaram Bheem','2019-03-21','technology')");

				statement.executeUpdate("INSERT INTO Editors VALUES (2,5000,'Permanent')");
				statement.executeUpdate("INSERT INTO Editors VALUES (3,5000,'Permanent')");
				statement.executeUpdate("INSERT INTO Editors VALUES (4,60,'Temporary')");
				statement.executeUpdate("INSERT INTO Editors VALUES (5,6000,'Permanent')");
				statement.executeUpdate("INSERT INTO Editors VALUES (6,50,'Temporary')");

				statement.executeUpdate("INSERT INTO Books VALUES (1, 11,1,'2002-03-02')");
				statement.executeUpdate("INSERT INTO Books VALUES (2, 12,1,'2002-03-02')");
				statement.executeUpdate("INSERT INTO Books VALUES (3, 13,1,'2002-03-02')");
				statement.executeUpdate("INSERT INTO Books VALUES (4, 14,1,'2002-03-02')");
				statement.executeUpdate("INSERT INTO Books VALUES (5, 15,1,'2002-03-02')");

				statement.executeUpdate("INSERT INTO Authors VALUES (7,9000,'Permanent')");
				statement.executeUpdate("INSERT INTO Authors VALUES (8,6000,'Permanent')");
				statement.executeUpdate("INSERT INTO Authors VALUES (9,60,'Temporary')");
				statement.executeUpdate("INSERT INTO Authors VALUES (10,6000,'Permanent')");
				statement.executeUpdate("INSERT INTO Authors VALUES (11,50,'Temporary')");

				statement.executeUpdate("INSERT INTO Periodicissues VALUES (6, '2019-03-02','Monthly')");
				statement.executeUpdate("INSERT INTO Periodicissues VALUES (7, '2019-04-07','Monthly')");
				statement.executeUpdate("INSERT INTO Periodicissues VALUES (8, '2019-06-04','Monthly')");
				statement.executeUpdate("INSERT INTO Periodicissues VALUES (9, '2019-03-20','Monthly')");
				statement.executeUpdate("INSERT INTO Periodicissues VALUES (10, '2019-09-08','Monthly')");

				statement.executeUpdate("INSERT INTO Chapters VALUES (1,'Introduction','how u doing???','2002-03-22')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (1,'Potter_head','The boy who lived','2002-03-22')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (2,'Introduction','Hello Readers','2003-04-07')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (2,'Beauty and the beast','He looked into her eyes','2003-04-07')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (3,'Introduction','Hello Readers','2005-06-04')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (3,'You can do it','Hustle...Hustle..Hustle','2005-06-04')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (4,'Intro','Hello Readers!!!','2008-03-20')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (4,'Eternal Flame','Darkness it glows','2008-03-20')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (5,'Intro','Hello Readers!!!','2010-09-08')");
				statement.executeUpdate("INSERT INTO Chapters VALUES (5,'Enter the Dragon','She braced the fire like a dragon','2010-09-08')");

				statement.executeUpdate("Insert into Distributorpayments VALUES(1,'2019-04-09',100);");
				statement.executeUpdate("Insert into Distributorpayments VALUES(2,'2019-04-10',100);");
				statement.executeUpdate("Insert into Distributorpayments VALUES(3,'2019-05-11',100);");
				statement.executeUpdate("Insert into Distributorpayments VALUES(4,'2019-06-12',100);");
				statement.executeUpdate("Insert into Distributorpayments VALUES(5,'2019-07-13',100);");
				statement.executeUpdate("Insert into Distributorpayments VALUES(6,'2019-08-14',100);");

				statement.executeUpdate("Insert into Edits VALUES(1,2);");
				statement.executeUpdate("Insert into Edits VALUES(2,3);");
				statement.executeUpdate("Insert into Edits VALUES(3,4);");
				statement.executeUpdate("Insert into Edits VALUES(4,5);");
				statement.executeUpdate("Insert into Edits VALUES(5,6);");
				statement.executeUpdate("Insert into Edits VALUES(6,2);");
				statement.executeUpdate("Insert into Edits VALUES(7,3);");
				statement.executeUpdate("Insert into Edits VALUES(8,4);");
				statement.executeUpdate("Insert into Edits VALUES(9,5);");
				statement.executeUpdate("Insert into Edits VALUES(10,6);");
				

				statement.executeUpdate("INSERT INTO Journalists VALUES (12,4000)");
				statement.executeUpdate("INSERT INTO Journalists VALUES (13,3000)");
				statement.executeUpdate("INSERT INTO Journalists VALUES (14,4500)");
				statement.executeUpdate("INSERT INTO Journalists VALUES (15,3500)");
				statement.executeUpdate("INSERT INTO Journalists VALUES (16,300)");

				statement.executeUpdate("Insert into StaffPayments VALUES(2,'2020-02-01',5000,'2020-02-02');");
				statement.executeUpdate("Insert into StaffPayments VALUES(3,'2020-02-01',5000,'2020-02-03');");
				statement.executeUpdate("Insert into StaffPayments VALUES(4,'2020-02-01',60,'2020-02-04');");
				statement.executeUpdate("Insert into StaffPayments VALUES(5,'2020-02-01',6000,'2020-02-05');");
				statement.executeUpdate("Insert into StaffPayments VALUES(6,'2020-02-01',50,'2020-02-06');");
				statement.executeUpdate("Insert into StaffPayments VALUES(7,'2020-02-01',9000,'2020-02-07');");
				statement.executeUpdate("Insert into StaffPayments VALUES(8,'2020-02-01',6000,'2020-02-08');");
				statement.executeUpdate("Insert into StaffPayments VALUES(9,'2020-02-01',60,'2020-02-09');");
				statement.executeUpdate("Insert into StaffPayments VALUES(10,'2020-02-01',6000,'2020-02-10');");
				statement.executeUpdate("Insert into StaffPayments VALUES(11,'2020-02-01',50,'2020-02-11');");
				statement.executeUpdate("Insert into StaffPayments VALUES(12,'2020-02-01',4000,'2020-02-12');");
				statement.executeUpdate("Insert into StaffPayments VALUES(13,'2020-02-01',3000,'2020-02-13');");	
				statement.executeUpdate("Insert into StaffPayments VALUES(14,'2020-02-01',4500,'2020-02-14');");
				statement.executeUpdate("Insert into StaffPayments VALUES(15,'2020-02-01',3700,'2020-02-15');");
				statement.executeUpdate("Insert into StaffPayments VALUES(16,'2020-02-01',300,'2020-02-15');");


				statement.executeUpdate("INSERT INTO Admin VALUES (1)");

				statement.executeUpdate("Insert into Orders VALUES(1,1,1,'Harry Potter');");
				statement.executeUpdate("Insert into Orders VALUES(1,2,2,'Harry Potter');");
				statement.executeUpdate("Insert into Orders VALUES(2,3,3,'Twilight');");
				statement.executeUpdate("Insert into Orders VALUES(2,4,4,'Twilight');");
				statement.executeUpdate("Insert into Orders VALUES(1,5,5,'Harry Potter');");
				statement.executeUpdate("Insert into Orders VALUES(1,6,6,'Harry Potter');");


				statement.executeUpdate("Insert into Articlewrites VALUES(6,12,'The Future');");
				statement.executeUpdate("Insert into Articlewrites VALUES(6,13,'New Releases');");
				statement.executeUpdate("Insert into Articlewrites VALUES(7,14,'Healthy Heart');");
				statement.executeUpdate("Insert into Articlewrites VALUES(7,15, 'NCSU Vs UNCC');");
				statement.executeUpdate("Insert into Articlewrites VALUES(8,16, 'Live your Life');");
				statement.executeUpdate("Insert into Articlewrites VALUES(8,12, 'Healthify');");
				statement.executeUpdate("Insert into Articlewrites VALUES(9,13,'Mr.Majnu');");
				statement.executeUpdate("Insert into Articlewrites VALUES(9,14, 'Awards');");
				statement.executeUpdate("Insert into Articlewrites VALUES(10,15, 'Movie Lovers');");
				statement.executeUpdate("Insert into Articlewrites VALUES(10,16, 'Upcoming movies');");


				statement.executeUpdate("Insert into Bookwrites VALUES(7,1);");
				statement.executeUpdate("Insert into Bookwrites VALUES(8,2);");
				statement.executeUpdate("Insert into Bookwrites VALUES(9,3);");
				statement.executeUpdate("Insert into Bookwrites VALUES(10,4);");
				statement.executeUpdate("Insert into Bookwrites VALUES(11,5);");

				connection.commit();
				System.out.println("Tables values inserted!");
			} catch (SQLException e) {
				connection.rollback();
				e.printStackTrace();
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
public static void main(String[] args) {
try {
// Loading the driver. This creates an instance of the driver
// and calls the registerDriver method to make MySql(MariaDB) Thin available to clients.


            Class.forName("org.mariadb.jdbc.Driver");

	    //connection = null;
            Statement statement = null;
            ResultSet result = null;

            try {
            // Get a connection instance from the first driver in the
            // DriverManager list that recognizes the URL jdbcURL
            connection = DriverManager.getConnection(jdbcURL, user, password);

		generatePrepStatements(connection);	
  	// Create a statement instance that will be sending
            // your SQL statements to the DBMS
            statement = connection.createStatement();
	    deleteAllTables(statement);
	    generateTables(connection,statement);	
        insert_demodata(connection,statement);
	    //insert_into_Tables(connection,statement);
            boolean exit= true;
            boolean bool= false;
            while(bool==false){
        printMainMenu();    
	String response=s.nextLine();
            switch(response){
                case "1":
                printEditMenu();
                break;
                case "2":
                printProdMenu();
                break;
                case "3":
                printDistMenu();
                break;
                case "4":
                printReportMenu();
                break;
                case "5":
                System.exit(0);
                break;
                default:
                System.out.println("Wrong input");
                break;
        }

            }
	    
            } finally {
                close(statement);
                close(connection);
            }
} catch(Throwable oops) {
            oops.printStackTrace();
        }
}
static void close(Connection connection) {
        if(connection != null) {
            try {
            connection.close();
            } catch(Throwable whatever) {}
        }
    }
    static void close(Statement statement) {
        if(statement != null) {
            try {
            statement.close();
            } catch(Throwable whatever) {}
        }
    }
    static void close(ResultSet result) {
        if(result != null) {
            try {
            result.close();
            } catch(Throwable whatever) {}
        }
    }
}
