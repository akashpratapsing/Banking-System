
export const SYSTEM1_TX = 'http://localhost:8081/system1/transaction';

export async function sendTransaction(user, tx) {
  const headers = {
    "Content-Type": "application/json",
    "Authorization": "Basic " + btoa(user.username + ":" + user.password)
  };
  const res = await fetch(SYSTEM1_TX, {
    method: 'POST',
    headers,
    body: JSON.stringify(tx)
  });
  const json = await res.json().catch(()=>({success:false, message:'Invalid response'}));
  return json;
}
