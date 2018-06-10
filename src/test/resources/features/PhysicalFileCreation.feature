Feature: Test file creation

  Scenario: Passing the structure of file
    Given 2 logical files for physical file "FileName"
    And file header for logical file 1
      | recordType | SessionType |
      | 1          |             |
    And 2 batch header for logical file 1
      | recordType | batchNumber |
      | 5          |             |
      | 5          |             |
    And 2 entry record for batch 1 in logical file 1
      | recordType | entryNumber |
      | 6          |             |
      | 6          |             |
    And additional record for batch 1 in logical file 1
      | recordType | bankName |
      | 7          |          |
      | 7          |          |
    And 3 entry record for batch 2 in logical file 1
      | recordType | entryNumber |
      | 6          |             |
      | 6          |             |
      | 6          |             |
    And additional record for batch 2 in logical file 1
      | recordType | bankName |
      | 7          |          |
      | 7          |          |
      | 7          |          |
    And batch control for logical file 1
      | recordType | batchNumber |
      | 8          |             |
      | 8          |             |

    And file control for logical file 1
      | recordType | SessionType |
      | 9          |             |

    ##Begin with next logical file

    And file header for logical file 2
      | recordType | SessionType |
      | 1          |             |
    And 1 batch header for logical file 2
      | recordType | batchNumber |
      | 5          |             |
    And 1 entry record for batch 1 in logical file 2
      | recordType | entryNumber |
      | 6          |             |
    And additional record for batch 1 in logical file 2
      | recordType | bankName |
      | 7          |          |
    And batch control for logical file 2
      | recordType | batchNumber |
      | 8          |             |
    And file control for logical file 2
      | recordType |
      | 9          |

    And physical model of NACHA-M file is ready
