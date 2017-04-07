<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Edit Bet">

<form action="/edit-bet?id=${bet.bet_id}" method="POST">
  <div class="form-group">
    <label for="title">Title of the bet</label>
    <input type="text" class="form-control" id="title" name="title" aria-describedby="titleHelp" value="${bet.title}">
  </div>
  <div class="form-group">
    <label for="description">Bet description</label>
    <textarea class="form-control" id="description" name="description" rows="3">${bet.description}</textarea>
  </div>
  <div class="row">
  	<div class="col-2 col-form-label">Initiator</div>
	<div class="col-10">
	    ${bet.initiator}
	</div>
  </div>
  <#if bet.challenger != "">
  <div class="row">
  	<div class="col-2 col-form-label">Challenger</div>
	<div class="col-10">
		${bet.challenger}
	</div>
  </div>
  <div class="form-group row">
    <label for="winner" class="col-2 col-form-label">Winner</label>
    <select>
      <option value="-1">-- not yet decided --</option>
      <option value="${bet.initiator_id}">${bet.initiator}</option>
      <option value="${bet.challenger_id}">${bet.challenger}</option>
    </select>
  </div>
  </#if>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</@layout.masterTemplate>