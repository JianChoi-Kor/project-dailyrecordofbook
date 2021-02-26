<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<script
	src="https://cdn.ckeditor.com/ckeditor5/25.0.0/classic/ckeditor.js"></script>


<h1>Classic editor</h1>
<textarea name="content" id="editor">
    	This is some sample content.
</textarea>

<script>
	ClassicEditor.create(document.querySelector('#editor'), {
		ckfinder : {
			uploadUrl : 'http://localhost:8080/board/imgUpload'
		}
	}).then(editor => {window.editor = editor})
	.catch(error => {
		console.error(error)
	})

</script>