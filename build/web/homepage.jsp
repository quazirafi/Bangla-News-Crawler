
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <head> 
        <title>jQuery MultiSelect Demo</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" href="assets/demo.css"></link>
        <link rel="stylesheet" href="assets/form-login.css"></link>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
        <script src="javascript/jquery.bgiframe.min.js" type="text/javascript"></script>
        <script src="javascript/jquery.multiSelect.js" type="text/javascript"></script>

        <link href="assets/jquery.multiSelect.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript">

            $(document).ready(function () {

                // Default options
                $("#control_1, #control_3, #control_4, #control_5").multiSelect();

                

            });

        </script>

        <style type="text/css">
            HTML {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
            }

            H2 {
                font-size: 14px;
                font-weight: bold;
                margin: 1em 0em .25em 0em;
            }

            P {
                margin: 1em 0em;
            }
            
        </style>
    </head>

    <body>
        <header>
            <h1>Bangla Corpus</h1>
            <a href="">Download</a>
        </header>
        <form style="margin-left: 500px" action="" method="post">



            <div class="form-white-background">
                <div class="form-title-row" >
                    <h1>Select Newspapers</h1>
                </div>
                <span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span>
                <select id="control_3" class="ui-icon ui-icon-triangle-2-n-s" name="newspaperName" multiple="multiple" size="5">
                    
                    <option value="" ></option>
                    <option value="prothomAlo" > ProthomAlo</option>
                    <option value="kalerKantho" > KalerKantho</option>
                    <option value="manabZamin" > ManabZamin</option>
                    <option value="bdnews24" > BdNews24</option>
                    <option value="banglanews24" > BanglaNews24</option>
                    <option value="ittefaq" > Ittefaq</option>
                </select>
                <div class="form-row form-last-row">
                    <button type="submit">Get Data</button>
                </div>
            </div>






        </form>





    </body>
</html>