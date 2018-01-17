import React from 'react';
import ProcedureList from './ProcedureList';
import _Select from './_Select';
import $$restapi from './$$restapi.js';

export default class WorkitemUpdate extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      addedProcedures: this.props.workitem.procedures,
      allProcedures: []
    };
    this.api = new $$restapi();
    this.api.doGetRequest(r => this.setState({allProcedures: r}),
        "/procedure/actual");
    this.workitem = JSON.parse(JSON.stringify(this.props.workitem));
    this.addProcedure = this.addProcedure.bind(this);
    this.removeProcedure = this.removeProcedure.bind(this);
    this.update = this.update.bind(this);
    this.delete = this.delete.bind(this);
    console.log("WorkitemUpdate#constructor");
  }

  convertDate(serverDate) {
    let split = serverDate.split("-");
    return split[2] + "-" + split[1] + "-" + split[0];
  }

  addProcedure(e) {
    const procedureName = e.target.textContent;
    this.workitem.procedures = this.workitem.procedures.concat(
        this.state.allProcedures.filter(
            item => item.procedure_name == procedureName));
    this.setState({addedProcedures: this.workitem.procedures});
  }

  removeProcedure(e) {
    const procedureName = e.target.textContent;
    this.workitem.procedures = this.workitem.procedures.filter(
        item => item.procedure_name != procedureName);
    this.setState({addedProcedures: this.workitem.procedures});
  }

  update() {
    let date = this.refs.dateIn.value;
    let time = this.refs.timeIn.value;
    let comment = this.refs.commentIn.value;

    this.workitem.service_date = this.convertDate(date);
    this.workitem.service_time = time;
    this.workitem.comment = comment;
    this.workitem.master_id = this.refs.masterSelected.state.value;
    console.log("updated workitem")
    console.table(this.workitem);

    this.api.doPostRequest(this.workitem, "/workitem/update", false,
        this.props.onUpdate);
  }

  delete() {
    this.api.doPostRequest("workitem_id=" + this.workitem.id
        , "/workitem/cancel"
        , true
        , this.props.onUpdate);
  }

  render() {
    const masters = this.props.masters;
    const {comment, service_time, service_date, procedures, client_details} = this.workitem;
    console.log("WorkitemUpdate#render");
    console.table(this.workitem);
    return (
        <div className="workitem-container block">

          <div className="border-container">
            <div className="phone-container block">
              <div className={"time"}>{client_details.name}</div>
              <div>{client_details.phones}</div>
            </div>
            <div className="time-container block">
              <_Select ref="masterSelected" map={masters.map((item) => {
                    return {value: item.id, key: item.name}
                  }
              )}/>
              <input type="date" ref="dateIn"
                     defaultValue={this.convertDate(service_date)}/>
              <input type="time" ref="timeIn" defaultValue={service_time}/>
            </div>
            <div className="procedures-container block">
              <ProcedureList list={this.state.allProcedures.map(
                  item => item.procedure_name)}
                             callback={this.addProcedure}/>
              <ProcedureList list={this.state.addedProcedures.map(
                  item => item.procedure_name)}
                             callback={this.removeProcedure}/>
            </div>
          </div>

          <div className="comment-container border-container">
            <textarea ref="commentIn" placeholder="Comment"
                      defaultValue={comment}></textarea>

            <div className="phone-container block">
              <div>
                <button className="wi-remove-btn" onClick={this.delete}>Delete
                </button>
                <button className="wi-update-btn" onClick={this.update}>Update
                </button>
              </div>
            </div>
          </div>

        </div>
    );
  }
}
