import React from 'react';
import Master from './Master';
import Week from './Week.js';
import $$restapi from './$$restapi.js';

export default class Page extends React.Component {
  constructor(props) {
    super(props);
    this.masterChangedCallback = this.masterChangedCallback.bind(this);
    this.state = {
      noMasters: false,
      masters: [],
      currentMasterId: -1
    };
    this.api = new $$restapi();
    this.api.doGetRequest(r => {
          if (r.length == 0) {
            this.setState({noMasters: true});
          } else {
            this.setState({masters: r, currentMasterId: r[0].id});
          }
        },
        "/person/masters");
  }

  masterChangedCallback(masterId) {
    console.log("master changed on " + masterId);
    if (masterId != this.state.currentMasterId) {
      this.setState({currentMasterId: masterId});
    }
  }

  render() {
    const cssStyle = "master_block";
    const masters = this.state.masters;
    const currentMasterId = this.state.currentMasterId;
    console.log("Page#render.currentMasterId:" + currentMasterId + "masters:");
    console.table(masters);
    if (this.state.noMasters) {
      return <h3>No master was found</h3>;
    }
    return (masters.length == 0)
        ? <div className="loading"></div>
        : (
            <div>
              <div className={cssStyle}>
                {
                  masters.map(
                      (item, i) => <Master
                          active={currentMasterId == item.id}
                          onMasterClick={this.masterChangedCallback}
                          key={i}
                          profile={item}/>)
                }
              </div>
              <Week masterid={currentMasterId} masters={masters}/>
            </div>
        )
  }
}
