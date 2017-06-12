<!DOCTYPE html>
<html>

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Corpus Home</title>

        <link rel="stylesheet" href="assets/demo.css">
        <link rel="stylesheet" href="assets/form-login.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<!--		<script src="javascript/jquery.bgiframe.min.js" type="text/javascript"></script>
		<script src="javascript/jquery.multiSelect.js" type="text/javascript"></script>

		<link href="assets/jquery.multiSelect.css" rel="stylesheet" type="text/css" />-->
        <script type="text/javascript">
			
			$(document).ready( function() {
				
				// Default options
				$("#control_1, #control_3, #control_4, #control_5").multiSelect();
				
				// With callback
				$("#control_6").multiSelect( null, function(el) {
					$("#callbackResult").show().fadeOut();
				});
				
				// Options displayed in comma-separated list
				$("#control_7").multiSelect({ oneOrMoreSelected: '*' });
				
				// 'Select All' text changed
				$("#control_8").multiSelect({ selectAllText: 'Pick &lsquo;em all!' });
				
				// Show test data
				$("FORM").submit( function() {
					$.post('result.php', $(this).serialize(), function(r) {
						alert(r);
					});
					return false;
				});
				
			});
			
		</script>
    </head>

    <body>
    <header>
        <h1>Bangla Corpus</h1>
        <a href="">Download</a>
    </header>




    <div class="main-content">

        <!-- You only need this form and the form-login.css -->

        <form class="form-login" method="post" action="UpdateCorpus">

            <div class="form-log-in-with-email">

                <div class="form-white-background">

                    <div class="form-title-row">
                        <h1>Select Newspapers</h1>
                    </div>

                    <div class="form-row">
                        <label>
                            <span>Prothom Alo</span>
                            <input type="checkbox" value="prothomAlo" name="newspaperName" >
                        </label>   
                        <label>
                            <span>Kaler Kantho</span>
                            <input type="checkbox" value="kalerKantho" name="newspaperName">
                        </label>
                        <label>
                            <span>Manab Zamin</span>
                            <input type="checkbox" value="manabZamin" name="newspaperName">
                        </label>   
                        <label>
                            <span>Bdnews24</span>
                            <input type="checkbox" value="bdnews24" name="newspaperName">
                        </label>
                        <label>
                            <span>Banglanews24</span>
                            <input type="checkbox" value="banglanews24" name="newspaperName">
                        </label>   
                        <label>
                            <span>Ittefaq</span>
                            <input type="checkbox" value="ittefaq" name="newspaperName">
                        </label>
                        <label>
                            <span>BdNews24</span>
                            <input type="checkbox" value="bdnews24" name="newspaperName">
                        </label>
                    </div>

                    <div class="form-title-row">
                        <h1>Select Categories</h1>
                    </div>


                    <div class="form-row">
                        <select id="control_3" multiple="multiple" size="1" name="category">
                                <option value="sports" >Sports</option>
                                <option value="entertainment" >Entertainment</option>
                                <option value="art-and-literature" >Literature</option>
                                <option value="international" >International</option>
                                <option value="education" >Education</option>
                                <option value="opinion" >Opinion</option>
                                <option value="politics" >Politics</option>
                                <option value="technology" >Technology</option>
                                <option value="crime" >Crime</option>
                                <option value="accident" >Accident</option>
                                <option value="economy" >Economy</option>
                                <option value="life-style" >LifeStyle</option>
                            </select>
                        
                    </div>
                    <div class="form-title-row">
                        <h1>Select Date Interval</h1>
                    </div>
                    <div class="form-row">
                        <div class="form-row">
                            <input type="text" name="dateFrom" placeholder="Date From">
                        </div>
                        <div class="form-row">
                            <input type="text" name="dateTo" placeholder="Date To">
                        </div>
                    </div>


                

                <div class="form-row form-last-row">
                    <button type="submit">Get Data</button>
                </div>

            </div>


        </form>

    </div>

</body>

</html>
