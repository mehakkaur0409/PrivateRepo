import logo from './logo.svg';
import './App.css';

const App = () => {
  const openInNewTab = url => {
    window.open(url, '_blank', 'noopener,noreferrer');
  };
  // 1. either create jenkins pipeline script
  // 2. read jenkins response api and proceed.
  // 3. slack build trigger. 

  //hit the pre smoke test.
  // wait for response on button click await.
  // if success but how ?(either read slack notification or read build success)->
  // - open 4 options of services in checkbox list. 
  // - which ever selected, trigger its job link.
  // else failure-> send slack notification
  return (
    <div>
      <button onClick={() => openInNewTab('https://jenkins.ci.appdirect.tools/job/ad-marketplace-checkout/job/ad_checkout_qa_auto_pre_deploy_smoke_test/job/%s/lastBuild/api/json')}>
        Open google
      </button>
    </div>
  );
};
export default App;
