import React, {Component} from 'react';

export default class Master extends Component {
  constructor(props) {
    super(props);
    this.onMasterClick = this.onMasterClick.bind(this);
  }

  onMasterClick(e) {
    this.props.onMasterClick(e.target.dataset.id);
  }

  render() {
    const cssStyle = this.props.active ? "master_photo active" : "master_photo";
    return (
        <div className={"master"}>
          <img className={cssStyle} src={"img/" + this.props.profile.photo}
               onClick={this.onMasterClick}
               data-id={this.props.profile.id}/>
          <div>{this.props.profile.first_name}</div>
        </div>
    )
  }
}
