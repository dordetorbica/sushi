<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Unchallenged Bets">
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
      <td><b>${bet.title}</b><br><i>${bet.description}</i></td>
      <td>${bet.initiator}</td>
      <td><a href="/accept-bet?id=${bet.bet_id}">Take Challenge</a></td>
      <td><a href="/edit-bet?id=${bet.bet_id}"><i class="fa fa-pencil"></a></i> <a href="/delete-bet?id=${bet.bet_id}"><i class="fa fa-trash"></i></a></td>
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
