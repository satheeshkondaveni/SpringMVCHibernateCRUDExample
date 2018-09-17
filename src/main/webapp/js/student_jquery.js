$(document).ready(function() {


  $('form[id="studentForm"]').validate({
    rules: {
    	studentName: 'required',
    	branch: 'required',
    	marks: {
            required: true,
            maxlength: 3,
          }
    },
    messages: {
    	studentName: 'This field is required',
    	branch: 'This field is required',
    	marks: {
    		required: 'This field is required',
    		maxlength: 'Maximum marks less than or equal 100'
      }
    },
    submitHandler: function(form) {
      form.submit();
    }
  });

});