<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Corpus Home</title>
        <link rel="stylesheet" href="assets/demo.css">
        <link rel="stylesheet" href="assets/form-login.css">
        <link rel="stylesheet" href="assets/bootstrap-select.css">
        <script src="javascript/bootstrap-select.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    </head>
    <body>
        <header>
            <h1>Bangla Corpus</h1>
            <a href="">Download</a>
        </header>
        <script>
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
                //alert(today);
                document.getElementById("datefield1").setAttribute("max", today);
                document.getElementById("datefield2").setAttribute("max", today);
            }
        </script>



        <form style="margin-left: 500px" action="UpdateCorpus" method="post">



            
                <div class="form-title-row" >
                    <h1>Please Select Data Information</h1>
                </div>


                <hr>
                    
                        <select id="done" class="selectpicker" multiple data-done-button="true">
    <option>Apple</option>
    <option>Banana</option>
    <option>Orange</option>
    <option>Pineapple</option>
    <option>Apple2</option>
    <option>Banana2</option>
    <option>Orange2</option>
    <option>Pineapple2</option>
    <option>Apple2</option>
    <option>Banana2</option>
    <option>Orange2</option>
    <option>Pineapple2</option>
  </select>
                    
                <hr>

                <!--                <div class="form-title-row" >
                                    <h1>Select Category</h1>
                                </div>-->
                <div class="multiselect" style="margin-top: 50px">
                    <div class="selectBox" onclick="showCheckboxes2()">
                        <select>
                            <option>Select Category</option>
                        </select>
                        <div class="overSelect"></div>
                    </div>
                    <div id="checkboxes2" style="display: none">
                        <label>
                            <input type="checkbox" value="sports" name="category" />Sports
                        </label>   
                        <label>
                            <input type="checkbox" value="entertainment" name="category" />Entertainment
                        </label>
                        <label>
                            <input type="checkbox" value="art-and-literature" name="category" />Literature
                        </label>   
                        <label>
                            <input type="checkbox" value="international" name="category" />International
                        </label>
                        <label>
                            <input type="checkbox" value="education" name="category" />Education
                        </label>
                        <label>
                            <input type="checkbox" value="opinion" name="category" />Opinion
                        </label>
                        <label>
                            <input type="checkbox" value="politics" name="category" />Politics
                        </label>
                        <label>
                            <input type="checkbox" value="technology" name="category" />Technology
                        </label>
                        <label>
                            <input type="checkbox" value="crime" name="category" />Crime
                        </label>
                        <label>
                            <input type="checkbox" value="accident" name="category" />Accident
                        </label>
                        <label>
                            <input type="checkbox" value="economy" name="category" />Economy
                        </label>
                        <label>
                            <input type="checkbox" value="life-style" name="category" />LifeStyle
                        </label>
                    </div>
                </div>
                <div class="multiselect" style="margin-top: 50px">
                    <div class="selectBox" onclick="showCheckboxes3()">
                        <select>
                            <option>Select Action</option>
                        </select>
                        <div class="overSelect"></div>
                    </div>
                    <div id="checkboxes3" style="display: none">
                        <label>
                            <input type="checkbox" value="download" name="action" />Download
                        </label>   
                        <label>
                            <input type="checkbox" value="update" name="action" />Update
                        </label>
                    </div>
                </div>
                <div style="margin-top: 50px">
                    <p>Start Point</p><br>
                     <input id="datefield1" name="dateFrom" type='date' min='1899-01-01' onclick="setDateToToday()" ></input>
                </div>
                <div style="margin-top: 30px">
                    <p>End Point</p><br>
                     <input id="datefield2" name="dateTo" type='date' min='1899-01-01' onclick="setDateToToday()" ></input>
                </div>
                <div>
                    <button type="submit" >Go</button>
                </div>
            
        </form>
    </body>
    <script>
        var expanded = false;
        var expanded2 = false;
        var expanded3 = false;
        function showCheckboxes() {
            var checkboxes = document.getElementById("checkboxes");
            if (!expanded) {
                checkboxes.style.display = "block";
                expanded = true;
            } else {
                checkboxes.style.display = "none";
                expanded = false;
            }
        }
        function showCheckboxes2() {
            var checkboxes2 = document.getElementById("checkboxes2");
            if (!expanded2) {
                checkboxes2.style.display = "block";
                expanded2 = true;
            } else {
                checkboxes2.style.display = "none";
                expanded2 = false;
            }
        }
        function showCheckboxes3() {
            var checkboxes3 = document.getElementById("checkboxes3");
            if (!expanded3) {
                checkboxes3.style.display = "block";
                expanded3 = true;
            } else {
                checkboxes3.style.display = "none";
                expanded3 = false;
            }
        }
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
                //alert(today);
                document.getElementById("datefield1").setAttribute("max", today);
                document.getElementById("datefield2").setAttribute("max", today);
            }
    </script>
    <style>

        button{
            text-align: center;
            display: block;
            border-radius: 2px;
            background-color: #6caee0;
            color: #ffffff;
            font-weight: bold;
            box-shadow: 1px 2px 4px 0 rgba(0, 0, 0, 0.08);
            padding: 15px 35px;
            border: 0;
            margin-top: 20px;
            cursor: pointer;
        }
        .multiselect {
            width: 200px;
        }

        .selectBox {
            position: relative;
        }

        .selectBox select {
            width: 100%;
            font-weight: bold;
        }

        .overSelect {
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
        }

        #checkboxes {
            display: none;
            border: 1px #dadada solid;
        }

        #checkboxes label {
            display: block;
        }

        #checkboxes label:hover {
            background-color: #1e90ff;
        }
        #checkboxes2 {
            display: none;
            border: 1px #dadada solid;
        }

        #checkboxes2 label {
            display: block;
        }

        #checkboxes2 label:hover {
            background-color: #1e90ff;
        }
        #checkboxes3 {
            display: none;
            border: 1px #dadada solid;
        }

        #checkboxes3 label {
            display: block;
        }

        #checkboxes3 label:hover {
            background-color: #1e90ff;
        }
    </style>
</html>

