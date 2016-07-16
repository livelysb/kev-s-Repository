<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div id="setting-window">
		<div id="setting-header">Setting</div>
		<div id="setting-content"><br>
	<label>내정보 : </label>
	<label class="switch switch-flat" >
		<input class="switch-input" type="checkbox" id="setting-myInfo"/>
		<span class="switch-label" data-on="On" data-off="Off"></span> 
		<span class="switch-handle"></span> 
	</label><br><br>
	<label>귓속말 : </label>
	<label class="switch switch-flat">
		<input class="switch-input" type="checkbox" id="setting-whisper"/>
		<span class="switch-label" data-on="On" data-off="Off"></span> 
		<span class="switch-handle"></span> 
	</label><br><br>
	<label>&nbsp;&nbsp;친구&nbsp;&nbsp;: </label>
	<label class="switch switch-flat">
		<input class="switch-input" type="checkbox" id="setting-friend"/>
		<span class="switch-label" data-on="On" data-off="Off"></span> 
		<span class="switch-handle"></span> 
	</label><br><br>
	<label>테마 : </label>
		<select class="form-control" id="setting-select" style="width: 150px">
			<option value="android">android</option>
			<option value="arctic">arctic</option>
			<option value="black">black</option>
			<option value="blackberry">blackberry</option>
			<option value="bootstrap">bootstrap</option>
			<option value="classic">classic</option>
			<option value="dark">dark</option>
			<option value="darkblue">darkblue</option>
			<option value="energyblue">energyblue</option>
			<option value="fresh">fresh</option>
			<option value="glacier">glacier</option>
			<option value="highcontrast">highcontrast</option>
			<option value="kokomo">kokomo</option>
			<option value="light">light</option>
			<option value="metro">metro</option>
			<option value="metrodark">metrodark</option>
			<option value="mobile">mobile</option>
			<option value="office">office</option>
			<option value="orange">orange</option>
			<option value="shinyblack">shinyblack</option>
			<option value="summer">summer</option>
			<option value="ui-darkness">ui-darkness</option>
			<option value="ui-le-frog">ui-le-frog</option>
			<option value="ui-lightness">ui-lightness</option>
			<option value="ui-overcast">ui-overcast</option>
			<option value="ui-redmond">ui-redmond</option>
			<option value="ui-smoothness">ui-smoothness</option>
			<option value="ui-start">ui-start</option>
			<option value="ui-sunny">ui-sunny</option>
			<option value="web">web</option>
			<option value="windowsphone">windowsphone</option>
			
		</select><br><br><br>
	<button type="button" id="setting-save" class="btn btn-success">&nbsp;&nbsp;저장&nbsp;&nbsp;</button>
	<button type="button" id="setting-initialization" class="btn btn-danger">초기화</button>
	</div>
</div>







