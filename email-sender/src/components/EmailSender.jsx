import React, { useState } from "react";
import toast from "react-hot-toast";

function EmailSender() {
  const [emailData, setEmailData] = useState({
    to: "",
    subject: "",
    message: "",
  });
  function handleFieldChange(event, name) {
    setEmailData({ ...emailData, [name]: event.target.value });
  }
  function handleSubmit(event) {
    if (
      emailData.to == "" ||
      emailData.subject == "" ||
      emailData.message == ""
    ) {
      toast.error("Invalid Fields !!");
      return;
    }

    console.log(emailData);
  }

  return (
    <div className="w-full min-h-screen flex justify-center items-center">
      <div className="email_card md:w-1/3 w-full mx-4 md:mx-0 -mt-10 p-4 rounded-lg border shadow bg-white">
        <h1 className="text-gray-900 text-3xl">Email Sender</h1>
        <p className="text-gray-700">
          {" "}
          Send Email To your favourite person with your own app
        </p>

        <form action="">
          <div className="input_field">
            <form className="max-w-md mx-auto">
              {/* TO  */}
              <label
                htmlFor="base-input"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                To
              </label>
              <input
                value={emailData.to}
                onChange={(event) => handleFieldChange(event, "to")}
                type="text"
                id="base-input"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              />
              {/* Subject  */}
              <label
                htmlFor="base-input"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Subject
              </label>
              <input
                value={emailData.subject}
                onChange={(event) => handleFieldChange(event, "subject")}
                type="text"
                id="base-input"
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              />
              {/* Message */}
              <div className="form_field mt-4">
                <label
                  htmlFor="message"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Your message
                </label>
                <textarea
                  value={emailData.message}
                  onChange={(event) => handleFieldChange(event, "message")}
                  id="message"
                  rows="6"
                  className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="Write your thoughts here..."
                ></textarea>
              </div>
              <div className="button_container mt-4 flex justify-center gap-2 mt-4">
                <button className="hover:bg-blue-900 text-white bg-blue-700 px-3 py-2 rounded">
                  Send Email
                </button>
                <button className="hover:bg-red-900 text-white bg-red-700 px-3 py-2 rounded">
                  Cancel Email
                </button>
              </div>
            </form>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EmailSender;
