<script type="text/javascript">
$('.newQuestion').click(function() {
  javascriptRoutes.controllers.Questions.add().ajax({
      success: function(data) {
          $("#result").html(data);
        },
        error: function() {
          alert("Error!")
        }
        });
});

$('.deleteQuestion').click(function() {
	  javascriptRoutes.controllers.Questions.delete(0).ajax({
	      success: function(data) {
	          $("#result").html(data);
	        },
	        error: function() {
	          alert("Error!")
	        }
	        });
	});


</script>