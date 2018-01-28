import React from 'react';
import WorkitemUpdate from './WorkitemUpdate';

export default class WorkItem extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      update: false,
      buttonName: "Update"
    }
    this.onCancel = this.onCancel.bind(this);
    this.onUpdate = this.onUpdate.bind(this);
    console.log("WorkItem#constructor");
    console.log("WI CONSTRUCT")
  }

  onCancel() {
    if (this.state.update) {
      this.setState({update: false, buttonName: "Update"});
    } else {
      this.setState({update: true, buttonName: "Cancel"});
    }
  }

  onUpdate() {
    this.onCancel();
    this.props.onChange();
  }

  render() {
    console.log("WorkItem#render");
    console.table(this.props.workitem)
    const {comment, service_time, procedures, client_details} = this.props.workitem;
    const commentBlock = comment
        ? <div className="comment">{comment}</div>
        : null;
    const updateBlock = this.state.update
        ? <WorkitemUpdate onUpdate={this.onUpdate}
                          workitem={this.props.workitem}
                          masters={this.props.masters}/>
        : null;
    return (
        <div className={"workitem"}>
          <div>
            <div>
              <span className="time">{service_time}-</span>
              {
                procedures.map((item, i) =>
                    <span className="time" key={i}>{item.procedure_name}.</span>
                )
              }
              <span
                  className="time">({client_details.name},[{client_details.phones}]).</span>
            </div>
            {commentBlock}
          </div>
          <button className={"update_workitem"}
                  onClick={this.onCancel}>{this.state.buttonName}</button>
          {updateBlock}
        </div>
    )
  }
}