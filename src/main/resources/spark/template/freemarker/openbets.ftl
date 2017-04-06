<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Running Bets">
<#if bets??>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Bet</th>
      <th>In Favor</th>
      <th>Against</th>
      <th>Actions</th>
    </tr>
  </thead>
  <tbody>
<#list bets as bet>
    <tr>
      <td>${bet.title}</td>
      <td>${bet.initiator}</td>
      <td>${bet.challenger}</td>
      <td><i class="fa fa-close"></i> <i class="fa fa-trash"></i></td>
    </tr>
</#list>
  </tbody>
</table>
<#else>
<h2>No Bets available</h2>
<p>
Currently no bets are available. Want to add some?
</p>
</#if>
</@layout.masterTemplate>