
<!DOCTYPE html>
<html>
    <head>
        <title>Bootstrap-select test page</title>

        <meta charset="utf-8">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/bootstrap-select.css">

        <style>
            body {
                padding-top: 70px;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="javascript/bootstrap-select.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Bangla News Corpus</a>
                </div>
            </div>
        </nav>

        <div class="container" style="text-align: center">
            <!--  <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header" style="float: right" >
                    <a class="navbar-brand" href="#">Admin Mode</a>
                  </div>
                </div>
                 .container-fluid 
              </nav>-->
            <hr>
            
            <form method="post" action="UpdateCorpus" >
                <label>Select Newspaper</label><br>
                <select id="first-disabled2" name="newspaperName" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                    <option value="prothomAlo" >Prothom Alo</option>
                    <option value="kalerKantho" >Kaler Kantho</option>
                    <option value="manabZamin" >Manab Zamin</option>
                    <option value="bdnews24" >Bdnews24</option>
                    <option value="banglanews24" >Banglanews24</option>
                    <option value="ittefaq" >Ittefaq</option>
                </select>
                <hr>
                <label>Select Category</label><br>
                <select id="first-disabled2" name="category" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
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
                    <option value="economy" >Economy </option>
                    <option value="life-style" > Lifestyle </option>
                </select>
                <hr>
                <label >Select Action</label ><br>
                <select id="first-disabled2" name="action" class="selectpicker" multiple data-hide-disabled="true" data-size="5">
                    <option value="download" > Download </option>
                    <option value="update" > Update </option>
                </select>
                <hr>
                <p>From <input id="datefield1" name="dateFrom" type='date' min='1899-01-01' onclick="setDateToToday()">&ensp;</input> To 
                    <input id="datefield2" name="dateTo" type='date' min='1899-01-01' onclick="setDateToToday()"></input></p><br>
                <button type="submit" class="btn btn-success">GO</button>
            </form>
            <hr>
        </div>

        <script>
            $(document).ready(function () {
                var mySelect = $('#first-disabled2');

                $('#special').on('click', function () {
                    mySelect.find('option:selected').prop('disabled', true);
                    mySelect.selectpicker('refresh');
                });

                $('#special2').on('click', function () {
                    mySelect.find('option:disabled').prop('disabled', false);
                    mySelect.selectpicker('refresh');
                });

                $('#basic2').selectpicker({
                    liveSearch: true,
                    maxOptions: 1
                });
            });
            function setDateToToday() {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth() + 1; //January is 0!
                var yyyy = today.getFullYear();
                if (dd < 10) {
                    dd = '0' + dd
                }
                if (mm < 10) {
                    mm = '0' + mm
                }
                today = yyyy + '-' + mm + '-' + dd;
                document.getElementById("datefield1").setAttribute("max", today);
                document.getElementById("datefield2").setAttribute("max", today);
            }
        </script>
    </body>
</html>
