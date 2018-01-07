import React from 'react';
import Week from "./week";
import Master from "./master";

export default class MasterBlock extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      currentMasterId: this.props.masters[0].id
    };
    this.masterChangedCallback = this.masterChangedCallback.bind(this);
  }

  masterChangedCallback(masterId) {
    console.log("master changed on " + masterId);
    if (masterId != this.state.currentMasterId) {
      this.setState({currentMasterId: masterId});
    }
  }

  render() {
    const cssStyle = "master_block";
    const masters = this.props.masters;
    console.log("Master#render");
    return (
        <div>
          <div className={cssStyle}>
            {
              masters.map(
                  (item, i) => <Master
                      active={this.state.currentMasterId == item.id}
                      onMasterClick={this.masterChangedCallback}
                      key={i}
                      profile={item}/>)
            }
          </div>
          <Week masterid={this.state.currentMasterId} masters={masters}/>
        </div>
    )
  }
}