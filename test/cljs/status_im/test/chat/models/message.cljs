(ns status-im.test.chat.models.message
  (:require [cljs.test :refer-macros [deftest is testing]]
            [status-im.chat.models.message :as message]))

(deftest add-to-chat?
  (testing "it returns true when it's not in loaded message"
    (is (message/add-to-chat? {:db {:chat {"a" {}}}}
                              {:message-id "message-id"
                               :chat-id "a"})))
  (testing "it returns false when it's already in the loaded message"
    (is (not (message/add-to-chat? {:db {:chats {"a" {:messages {"message-id" {}}}}}}
                                   {:message-id "message-id"
                                    :chat-id "a"}))))
  (testing "it returns false when it's already in the not-loaded-message-ids"
    (is (not (message/add-to-chat? {:db {:chats {"a" {:not-loaded-message-ids #{"message-id" }}}}}
                                   {:message-id "message-id"
                                    :chat-id "a"})))))
