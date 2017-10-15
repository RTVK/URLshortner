function validate() {
	var longUrl = document.getElementById("longUrl").value;
	if (longUrl == '') {
		alert('Please enter a valid URL.');
		return false;
	} else {
		return true;
	}
}