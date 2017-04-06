<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Edit Bet">
<form>
  <div class="form-group">
    <label for="title">Title of the bet</label>
    <input type="text" class="form-control" id="title" aria-describedby="titleHelp" placeholder="Will Trump stay for 4 years?">
    <small id="titleHelp" class="form-text text-muted">Here you enter a one-liner describing the bet.</small>
  </div>
  <div class="form-group">
    <label for="description">Bet description</label>
    <textarea class="form-control" id="description" rows="3">Trump will stay in office for at least four full years as elected</textarea>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</@layout.masterTemplate>